package application.outbound.email;

import com.sun.jersey.api.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan on 23/05/2016.
 */

@Service
public class EmailService {

    private static final Logger log = Logger.getLogger( EmailService.class.getName() );

    @Autowired
    private MailGunClient mailGunClient;

    public void sendWelcomeMessage(String toEmail) {

        String fromEmail = "Gift App <noreply@giftapp.com>";
        String subject = "Welcome to Gift app";
        String text = "Welcome message :)";

        ClientResponse clientResponse = mailGunClient.sendEmail(fromEmail, toEmail, subject, text);

        if (clientResponse.getStatus() != 200) {
            log.log(Level.WARNING, "Failed to send welcome message to: " + toEmail);
        } else {
            log.log(Level.INFO, "Welcome message sent to: " + toEmail);
        }
    }

}
