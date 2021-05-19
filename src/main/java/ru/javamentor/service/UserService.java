package ru.javamentor.service;

import ru.javamentor.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAllUsers();

    User deleteUser(Long parseUnsignedInt);

    void createOrUpdateUser(User user);
}
