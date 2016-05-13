package application.restControllers;

import application.repositories.gift.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/gift")
public class GiftController {

    private static final Logger log = Logger.getLogger( UserController.class.getName() );

    @Autowired
    @Qualifier("giftRepository")
    private GiftRepository giftRepository;

    //REST ENDPOINTS
}
