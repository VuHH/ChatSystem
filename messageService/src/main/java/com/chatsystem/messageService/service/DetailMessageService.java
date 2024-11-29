package com.chatsystem.messageService.service;

import com.chatsystem.messageService.entity.DetailMessage;
import com.chatsystem.messageService.repository.DetailMessageRepository;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

@Service
public class DetailMessageService {
  private final DetailMessageRepository detailMessageRepository;

  public DetailMessageService(DetailMessageRepository detailMessageRepository) {
    this.detailMessageRepository = detailMessageRepository;
  }

  public DetailMessage saveDetailMessage(DetailMessage detailMessage) {
    return detailMessageRepository.save(detailMessage);
  }
}
