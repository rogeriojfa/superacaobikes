package br.com.superacaobikes.admin.config;

import br.com.superacaobikes.admin.services.DBService;
import br.com.superacaobikes.admin.services.EmailService;
import br.com.superacaobikes.admin.services.SmtpMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }


    @Bean
    public boolean InstantiateDatabase() throws ParseException {

        if (!"create".equals(strategy)){
            return false;
        }
        dbService.InstantiateTesteDatabse();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpMailService();
    }


}
