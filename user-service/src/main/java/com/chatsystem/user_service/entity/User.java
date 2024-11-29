package com.chatsystem.user_service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    private String id;
    private String phoneNumber;
    private String name;
}
