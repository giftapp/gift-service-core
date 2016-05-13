package application;

/**
 * Created by matan on 09/05/2016.
 */

import application.model.Event;
import application.model.Hall;
import application.model.User;
import application.repositories.event.EventRepository;
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
    CommandLineRunner init(UserRepository userRepository, HallRepository hallRepository, EventRepository eventRepository) {
        return (evt) -> {
            userRepository.deleteAll();
            hallRepository.deleteAll();
            eventRepository.deleteAll();

            User userMatan = new User("Matan", "Lachmish", "m@gmail.com", "1234");
            userRepository.save(userMatan);

            Hall havatRonit = new Hall("Havat Ronit", "adress1",null);
            hallRepository.save(havatRonit);

            Event wedding1 = new Event(new Date(), "Jay", "Clair", havatRonit.getId());
            eventRepository.save(wedding1);

            wedding1.addUser(userMatan.getId());
            userMatan.addEvent(wedding1.getId());
            havatRonit.addEvent(wedding1.getId());

            hallRepository.save(havatRonit);
            eventRepository.save(wedding1);
            userRepository.save(userMatan);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
