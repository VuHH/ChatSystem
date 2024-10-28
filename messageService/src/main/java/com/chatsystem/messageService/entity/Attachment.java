package com.chatsystem.messageService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
public class Attachment {
    private String url;
    private String contentType;
    private String name;
    private long size;
}
