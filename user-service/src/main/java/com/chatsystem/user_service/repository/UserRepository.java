package com.chatsystem.user_service.repository;

import com.chatsystem.user_service.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    User findUserByPhoneNumber(String phoneNumber);
}
