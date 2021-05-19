package ru.javamentor.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.javamentor.repository.UserRepository;
import ru.javamentor.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public void createOrUpdateUser(User user) {
        if (null == user.getId()) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private void createUser(User user) {
        userRepository.createUser(user);
    }

    private void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public User deleteUser(Long id) {
        User user = null;
        user = userRepository.deleteUser(id);
        return user;
    }
}
