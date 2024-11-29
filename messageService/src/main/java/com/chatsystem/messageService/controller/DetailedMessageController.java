package com.chatsystem.messageService.controller;

import com.chatsystem.messageService.entity.DetailMessage;
import com.chatsystem.messageService.service.DetailMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detail-message")
public class DetailedMessageController {

  private final DetailMessageService detailMessageService;

  public DetailedMessageController(DetailMessageService detailMessageService) {
    this.detailMessageService = detailMessageService;
  }

  @PostMapping
  public DetailMessage saveDetailMessage(@RequestBody DetailMessage detailMessage) {
    return detailMessageService.saveDetailMessage(detailMessage);
  }
}
