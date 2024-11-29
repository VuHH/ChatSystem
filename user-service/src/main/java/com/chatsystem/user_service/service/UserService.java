package com.chatsystem.user_service.service;

import com.chatsystem.user_service.entity.User;
import com.chatsystem.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        User userFind = findUser(user.getPhoneNumber());
        if (userFind != null) {
            userRepository.save(user);
        }
    }

    public User findUser(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }
}
