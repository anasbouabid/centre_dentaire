package com.anasbouabid.centre_dentaire.service;

import com.anasbouabid.centre_dentaire.models.User;
import com.anasbouabid.centre_dentaire.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return user;
    }

    @Override
    public void saveOrUpdateUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
}
