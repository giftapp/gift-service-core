package application.outbound;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by matan,
 * On 23/09/2016.
 */

@Service
@ConfigurationProperties(prefix="outbound")
public class OutboundProperties {

    /**
     * Disable SMS service
     */
    private Boolean smsDisabled = false;

    /**
     * Disable Email service
     */
    private Boolean emailDisabled = false;

    /**
     * Getters & Setters
     */
    public Boolean getSmsDisabled() {
        return smsDisabled;
    }

    public void setSmsDisabled(Boolean smsDisabled) {
        this.smsDisabled = smsDisabled;
    }

    public Boolean getEmailDisabled() {
        return emailDisabled;
    }

    public void setEmailDisabled(Boolean emailDisabled) {
        this.emailDisabled = emailDisabled;
    }

}