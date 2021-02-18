package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.repositories.ClienteRepository;
import br.com.superacaobikes.admin.services.exception.ObjectNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final ClienteRepository cliRep;
    private final BCryptPasswordEncoder pe;
    private final EmailService emlSrv;

    private Random rand = new Random();

    public AuthService(ClienteRepository cliRep, BCryptPasswordEncoder pe, EmailService emlSrv) {
        this.cliRep = cliRep;
        this.pe = pe;
        this.emlSrv = emlSrv;
    }

    public void sendNewPassword(String email){

        Cliente cliente = cliRep.findByEmail(email);
        if (cliente == null){
            throw new ObjectNotFoundException("Email não encontrado");
        }
        String newPass = newPassWord();
        cliente.setSenha(pe.encode(newPass));
        cliRep.save(cliente);
        emlSrv.sendNewPasswordEmail(cliente, newPass);


    }

    private String newPassWord() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0){ // gera dígito
            return (char) (rand.nextInt(10) + 48 );
        }
        else if(opt == 1){ // gera letra maiúscula
            return (char) (rand.nextInt(26) + 65 );
        }
        else{ // gera letra minúscula
            return (char) (rand.nextInt(26) + 97 );
        }
    }
}
