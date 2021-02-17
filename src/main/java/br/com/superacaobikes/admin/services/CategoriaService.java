package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Categoria;
import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.dto.CategoriaDTO;
import br.com.superacaobikes.admin.repositories.CategoriaRepository;
import br.com.superacaobikes.admin.services.exception.DataIntegrityException;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository catRep;

    public CategoriaService(CategoriaRepository catRep) {
        this.catRep = catRep;
    }

    public Categoria find(Integer id){
        Optional<Categoria> obj = catRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return catRep.save(obj);
    }

    public Categoria update(Categoria obj){
        Categoria newCat = find(obj.getId());
        updateData(newCat, obj);
        return catRep.save(newCat);
    }
    private void updateData(Categoria newCat, Categoria obj) {
        newCat.setNome(obj.getNome());
    }

    public void delete(Integer id){
        find(id);
        try {
            catRep.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
           throw  new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll(){
        return catRep.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return catRep.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }
}
