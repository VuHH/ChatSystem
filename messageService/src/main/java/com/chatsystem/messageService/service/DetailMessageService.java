package com.chatsystem.messageService.service;

import com.chatsystem.messageService.entity.DetailMessage;
import com.chatsystem.messageService.repository.DetailMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DetailMessageService {
  @Autowired DetailMessageRepository detailMessageRepository;



  public DetailMessage saveDetailMessage(DetailMessage detailMessage) {
    return detailMessageRepository.save(detailMessage);
  }


}
