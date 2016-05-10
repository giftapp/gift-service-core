package application.model;

import java.util.Date;

/**
 * Created by matan on 09/05/2016.
 */
public class Ping {

    private final Date date;

    public Ping() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }
}
