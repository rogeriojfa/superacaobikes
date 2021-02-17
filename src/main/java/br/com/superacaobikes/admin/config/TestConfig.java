package br.com.superacaobikes.admin.config;

import br.com.superacaobikes.admin.services.DBService;
import br.com.superacaobikes.admin.services.EmailService;
import br.com.superacaobikes.admin.services.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }


    @Bean
    public boolean InstantiateDatabase() throws ParseException {
        dbService.InstantiateTesteDatabse();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
