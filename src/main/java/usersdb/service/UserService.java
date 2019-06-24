package usersdb.service;

import usersdb.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User user, boolean isNewUser);

    User getUser(int theId);

    void deleteUser(int theId);

    User findByUsername(String username);

    void registerNewUser(String username, String password);

    User findById(long id);

}
