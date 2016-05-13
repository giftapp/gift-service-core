package application;

/**
 * Created by matan on 09/05/2016.
 */

import application.model.*;
import application.repositories.event.EventRepository;
import application.repositories.gift.GiftRepository;
import application.repositories.hall.HallRepository;
import application.repositories.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Application {
    @Bean
    CommandLineRunner init(UserRepository userRepository, HallRepository hallRepository, EventRepository eventRepository, GiftRepository giftRepository) {
        return (evt) -> {
            userRepository.deleteAll();
            hallRepository.deleteAll();
            eventRepository.deleteAll();
            giftRepository.deleteAll();

            User userMatan = new User("Matan", "Lachmish", "m@gmail.com", "1234");
            userRepository.save(userMatan);

            Hall havatRonit = new Hall("Havat Ronit", "adress1",null);
            hallRepository.save(havatRonit);

            Event wedding1 = new Event(new Date(), "Jay", "Clair", havatRonit.getId());
            eventRepository.save(wedding1);

            wedding1.addUser(userMatan.getId());
            eventRepository.save(wedding1);

            Payment payment = new Payment(userMatan.getId(), wedding1.getId(), 1000, 2);
            Toast toast = new Toast(userMatan.getId(), wedding1.getId());
            toast.setText("Good luck!");
            Gift gift = new Gift(userMatan.getId(), wedding1.getId());
            gift.setPayment(payment);
            gift.setToast(toast);
            giftRepository.save(gift);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
