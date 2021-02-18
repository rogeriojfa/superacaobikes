package br.com.superacaobikes.admin;

import br.com.superacaobikes.admin.services.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperacaobikesApplication implements CommandLineRunner {

    private final S3Service s3Service;

    public SuperacaobikesApplication(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SuperacaobikesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        s3Service.uploadFile("C:\\temp\\duke-nukem.jpg");
    }
}
