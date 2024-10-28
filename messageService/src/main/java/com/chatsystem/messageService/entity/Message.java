package com.chatsystem.messageService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Data
public class Message {
    @Id
    private String id;
    private long timestamp;
    private String conversation;
    private List<Attachment> attachments;
}
