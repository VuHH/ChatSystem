package com.chatsystem.messageService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "detail-message")
@NoArgsConstructor
@Data
public class DetailMessage {
    @Id
    private String id;
    private Sender sender;
    private Receiver receiver;
    private Message message;

    @NoArgsConstructor
    @Data
    public static class Sender {
        private String phoneNumber;
        private String displayName;
    }
    @NoArgsConstructor
    @Data
    public static class Receiver {
        private String type;
        private String id;
    }

}
