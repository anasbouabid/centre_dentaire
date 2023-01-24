package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    void saveOrUpdateUser(User user);

    void deleteUserById(long id);

}
