package com.az.example.component;

import com.az.example.dto.EmailRequest;
import com.az.example.dto.EmailResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailUtil {

    @Value("${senderEmail:AzharMobeen@gmail.com}")
    private String senderEmail;

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    public EmailResponse sendSimpleEmail(EmailRequest request) {
        EmailResponse response = new EmailResponse();
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(request.getSenderEmail());
            message.setTo(request.getReceiverEmail());
            message.setSubject(request.getSubject());
            message.setText(request.getMessage());
            javaMailSender.send(message);
            response.setMessage("Successfully send to : "+ request.getReceiverEmail());
            response.setStatus(Boolean.TRUE);
        } catch (Exception exception) {
            log.error("Exception occurs: ", exception);
            response.setStatus(Boolean.FALSE);
            response.setMessage("Exception occurs, please check logs");
        }
        return response;
    }

    public EmailResponse sendEmail(EmailRequest request) {
        EmailResponse response = new EmailResponse();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            mimeMessageHelper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            Template template = configuration.getTemplate("email-template.ftl");
            Map<String , Object> model = new HashMap<>();
            model.put("userName", request.getUserName());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setTo(request.getReceiverEmail());
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(request.getSubject());
            mimeMessageHelper.setFrom(senderEmail);

            javaMailSender.send(message);
            // If there is no error then
            response.setMessage("Successfully send to : "+ request.getReceiverEmail());
            response.setStatus(Boolean.TRUE);

        } catch (Exception exception) {
            log.error("Exception occurs: ", exception);
            response.setStatus(Boolean.FALSE);
            response.setMessage("Exception occurs, please check logs");
        }
        return response;
    }
}
