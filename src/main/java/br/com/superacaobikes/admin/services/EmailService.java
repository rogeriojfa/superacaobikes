package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
