package usersdb.dao;

import usersdb.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> getUsers();

    public void saveUser(User user);

    public User getUser(int theId);

    public void deleteUser(int theId);

    public void updateUser(User user);

    public User findByUsername(String username);
}
