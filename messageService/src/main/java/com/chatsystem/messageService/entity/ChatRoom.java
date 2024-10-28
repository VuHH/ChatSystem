package com.chatsystem.messageService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;



@Document(collection = "chatrooms")
@NoArgsConstructor
@Data
public class ChatRoom {
    @Id
    private String id;
    private String name;
    private long timestamp;
    private String type;
    private List<User> members;
}
