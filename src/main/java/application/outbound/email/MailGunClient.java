package application.outbound.email;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

/**
 * Created by matan on 23/05/2016.
 */

@Component
public class MailGunClient {

    private static final Logger logger = LoggerFactory.getLogger(MailGunClient.class);

    private static final String MAILGUN_API_KEY = "key-0250569a98ad20acaba8a5448f3eb1ba";
    private static final String MAILGUN_DOMAIN_NAME = "sandboxd4f5a746903047ba80b2396776073fb1.mailgun.org";


    public ClientResponse sendEmail(String fromEmail, String toEmail, String subject, String text) {
        logger.debug("Sending email via Mailgun v3 API");
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", MAILGUN_API_KEY));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN_NAME + "/messages");

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", fromEmail);
        formData.add("to", toEmail);
        formData.add("subject", subject);
        formData.add("text", text);

        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }
}
