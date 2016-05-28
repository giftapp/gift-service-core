package application.outbound.sms;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan,
 * On 28/05/2016.
 */

@Component
public class TwilioClient {

    private static final Logger log = Logger.getLogger( TwilioClient.class.getName() );

    private static final String TWILIO_ACCOUNT_SID = "AC8d49d289b47a1874481f8d4a2710e511";
    private static final String TWILIO_AUTH_TOKEN = "c6632ffc3eb92d1e55a4171e5e8d9f05";
    private static final String TWILIO_PHONE_NUMBER = "+123123"; //TODO: update number

    private TwilioRestClient client = new TwilioRestClient(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);


    public void sendSMS(String toNumber, String text) {
        Account account = this.client.getAccount();

        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", toNumber));
        params.add(new BasicNameValuePair("From", TWILIO_PHONE_NUMBER));
        params.add(new BasicNameValuePair("Body", text));
        try {
            Message sms = messageFactory.create(params);
        } catch (TwilioRestException e) {
            log.log(Level.SEVERE, "Failed sending SMS: " + e);
        }
    }

}
