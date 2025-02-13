package com.moditha.group_finder.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
