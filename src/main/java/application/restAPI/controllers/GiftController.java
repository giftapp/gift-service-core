package application.restAPI.controllers;

import application.repositories.gift.GiftRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by matan on 13/05/2016.
 */

@RestController
@RequestMapping("/gift")
public class GiftController {

    private static final Logger logger = LoggerFactory.getLogger(GiftController.class);

    @Autowired
    private GiftRepository giftRepository;

    //REST ENDPOINTS
}
