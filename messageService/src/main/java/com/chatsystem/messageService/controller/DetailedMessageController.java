package com.chatsystem.messageService.controller;

import com.chatsystem.messageService.entity.DetailMessage;
import com.chatsystem.messageService.service.DetailMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/detail-message")
public class DetailedMessageController {

  @Autowired DetailMessageService detailMessageService;

  @PostMapping
  public DetailMessage saveDetailMessage(@RequestBody DetailMessage detailMessage) {
    return detailMessageService.saveDetailMessage(detailMessage);
  }


}
