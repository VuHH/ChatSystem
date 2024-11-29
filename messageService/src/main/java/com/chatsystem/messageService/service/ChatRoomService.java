package com.chatsystem.messageService.service;

import com.chatsystem.messageService.entity.ChatRoom;
import com.chatsystem.messageService.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserService userService) {
        this.chatRoomRepository = chatRoomRepository;
        this.userService = userService;
    }

    public ChatRoom createChatRoom(ChatRoom chatRoom) throws Exception {
        userService.createUser(chatRoom.getMembers());
        return chatRoomRepository.save(chatRoom);
    }
}
