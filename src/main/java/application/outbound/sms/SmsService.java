package application.outbound.sms;

import application.outbound.OutboundProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by matan,
 * On 28/05/2016.
 */

@Service
public class SMSService {

    private static final Logger logger = LoggerFactory.getLogger(SMSService.class);

    @Autowired
    OutboundProperties outboundProperties;

    @Autowired
    private TwilioClient twilioClient;

    public void sendVerificationSMS(String toNumber, String verificationCode) {
        if (outboundProperties.getSmsDisabled()) {
            logger.info("Skipping SMS sending , SMS service is disabled");
            return;
        }

        String text = "Welcome to Gift App, your verification code is: " + verificationCode;

        twilioClient.sendSMS(toNumber,text);
        logger.info("Verification SMS sent to: " + toNumber);
    }

}
