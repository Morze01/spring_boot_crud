package usersdb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usersdb.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int theId) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id=:ID", User.class);
        return query.setParameter("ID",theId)
                    .getResultList()
                    .stream()
                    .findAny()
                    .orElse(null);
    }

    @Override
    public void deleteUser(int theId) {
        User user = entityManager.find(User.class,theId);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username=:Username", User.class)
                            .setParameter("Username",username)
                            .getResultList()
                            .stream()
                            .findAny()
                            .orElse(null);
    }
}
