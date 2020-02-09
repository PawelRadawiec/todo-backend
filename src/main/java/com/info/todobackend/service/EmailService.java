package com.info.todobackend.service;

import com.info.todobackend.model.EmailDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }


    public EmailDto senEmail(EmailDto email) throws MessagingException {
        Context ctx = new Context();

        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message = prepareMessage(mimeMessage, email);

        String htmlContent = this.templateEngine.process(email.getTemplateName(), ctx);
        message.setText(htmlContent, true /* isHtml */);
        email.setEmailedMessage(htmlContent);
        this.mailSender.send(mimeMessage);

        this.templateEngine.clearTemplateCache();

        return email;

    }

    private MimeMessageHelper prepareMessage(MimeMessage mimeMessage, EmailDto emailDto)
            throws MessagingException {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                "UTF-8");
        message.setSubject(emailDto.getSubject());
        message.setFrom(emailDto.getFrom());
        message.setTo(emailDto.getTo());
        return message;
    }


}
