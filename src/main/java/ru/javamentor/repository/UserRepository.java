package ru.javamentor.repository;

import ru.javamentor.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User deleteUser(long id);

    User findById(Long id);
}
