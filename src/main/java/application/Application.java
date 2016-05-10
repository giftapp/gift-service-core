package application;

/**
 * Created by matan on 09/05/2016.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
//        return (evt) -> Arrays.asList(
//                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//                .forEach(
//                        a -> {
//                            User user = userRepository.save(new User(a, "last", "email", "password"));
//                        });
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}