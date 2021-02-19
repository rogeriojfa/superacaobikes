package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.*;
import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.domain.enums.Perfil;
import br.com.superacaobikes.admin.domain.enums.TipoCliente;
import br.com.superacaobikes.admin.dto.ClienteDTO;
import br.com.superacaobikes.admin.dto.ClienteNewDTO;
import br.com.superacaobikes.admin.repositories.ClienteRepository;
import br.com.superacaobikes.admin.repositories.EnderecoRepository;
import br.com.superacaobikes.admin.security.UserSS;
import br.com.superacaobikes.admin.services.exception.AuthorizationException;
import br.com.superacaobikes.admin.services.exception.DataIntegrityException;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final BCryptPasswordEncoder pe;
    private final ClienteRepository cliRep;
    private final EnderecoRepository endRep;
    private final S3Service s3Service;
    private final ImageService imgSrv;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public ClienteService(BCryptPasswordEncoder pe, ClienteRepository cliRep, EnderecoRepository endRep, S3Service s3Service, ImageService imgSrv) {
        this.pe = pe;
        this.cliRep = cliRep;
        this.endRep = endRep;
        this.s3Service = s3Service;
        this.imgSrv = imgSrv;
    }

    public Cliente find(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = cliRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = cliRep.save(obj);
        endRep.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newCli = find(obj.getId());
        updateData(newCli, obj);
        return cliRep.save(newCli);
    }

    private void updateData(Cliente newCli, Cliente obj) {
        newCli.setNone(obj.getNone());
        newCli.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            cliRep.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que tenha pedidos");
        }
    }

    public List<Cliente> findAll() {
        return cliRep.findAll();
    }

    public Cliente findByEmail(String email){
        UserSS user = UserService.authenticated();
        if (user == null || user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }
        Cliente obj = cliRep.findByEmail(email);
        if (obj == null){
            throw new ObjectNotFoundException("Objeto não encotrado! Id: " + user.getId() +
                    ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return cliRep.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    public URI uploadProfilePicture(MultipartFile multipartFile){
        UserSS user = UserService.authenticated();
        if (user == null){
            throw new AuthorizationException("Acesso negado");
        }
        BufferedImage jpgImage = imgSrv.getJpgImageFromFile(multipartFile);
        jpgImage = imgSrv.cropSquare(jpgImage);
        jpgImage = imgSrv.resize(jpgImage, size);
        String fileName = prefix + user.getId() + ".jpg";
        return s3Service.uploadFile(imgSrv.getInputStream(jpgImage, "jpg"), fileName, "image");
     }


}
