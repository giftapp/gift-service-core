package application.outbound.email;

import application.outbound.OutboundProperties;
import com.sun.jersey.api.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matan on 23/05/2016.
 */

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    OutboundProperties outboundProperties;

    @Autowired
    private MailGunClient mailGunClient;

    public void sendWelcomeMessage(String toEmail) {
        if (outboundProperties.getEmailDisabled()) {
            logger.info("Skipping Email sending , Email service is disabled");
            return;
        }

        String fromEmail = "Gift App <noreply@giftapp.co.il>";
        String subject = "Welcome to Gift app";
        String text = "Welcome message :)";

        ClientResponse clientResponse = mailGunClient.sendEmail(fromEmail, toEmail, subject, text);

        if (clientResponse.getStatus() != 200) {
            logger.warn("Failed to send welcome message to: " + toEmail);
        } else {
            logger.info("Welcome message sent to: " + toEmail);
        }
    }

}
