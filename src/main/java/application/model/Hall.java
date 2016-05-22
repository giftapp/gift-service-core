package application.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by matan on 13/05/2016.
 */

@Document
public class Hall extends PersistedObject implements Serializable {

    @Indexed
    @NotNull
    private String name;

    @Indexed
    @NotNull
    private String address;

    private String URL;

    public Hall(String name, String address, String URL) {
        this.name = name;
        this.address = address;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
