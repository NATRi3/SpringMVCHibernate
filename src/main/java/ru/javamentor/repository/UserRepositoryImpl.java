package ru.javamentor.repository;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import ru.javamentor.exception.UserNotFoundException;
import ru.javamentor.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Log4j2
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User deleteUser(long id) throws NullPointerException {
        User user = findById(id);
        if (null == user) {
            log.warn("User not found" + id);
            throw new UserNotFoundException("User not found");
        }
        entityManager.remove(user);
        entityManager.flush();
        return user;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
