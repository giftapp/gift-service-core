package application.configuration;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matan,
 * On 28/05/2016.
 */

@Component
public class PropertiesConfiguration {

    private static final Logger log = Logger.getLogger( PropertiesConfiguration.class.getName() );

    private static final String PROPERTY_FILE = "config.properties";
    private static final String SMS_DISABLED_PROPERTY = "sms.disabled";

    private Boolean smsDisabled;

    public Boolean getSmsDisabled() {
        return smsDisabled;
    }

    public PropertiesConfiguration() {
        try {
            loadPropertiesValues();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Configuration file failed to load: " + e);
        }
    }

    private void loadPropertiesValues() throws IOException {

        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = PROPERTY_FILE;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            smsDisabled = Boolean.valueOf(prop.getProperty(SMS_DISABLED_PROPERTY));

            log.log(Level.INFO, "Configuration file loaded");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Configuration file failed to load: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
