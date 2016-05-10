package application.restControllers;

/**
 * Created by matan on 09/05/2016.
 */

import application.model.Ping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @RequestMapping(method= RequestMethod.GET)
    public Ping ping() {
        return new Ping();
    }

}
