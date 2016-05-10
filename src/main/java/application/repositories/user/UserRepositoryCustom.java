package application.repositories.user;

import application.model.User;

import java.util.List;

/**
 * Created by matan on 10/05/2016.
 */
public interface UserRepositoryCustom {
    List<User> getUsersName(User users);
}
