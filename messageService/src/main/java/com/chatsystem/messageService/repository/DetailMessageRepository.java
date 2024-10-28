package com.chatsystem.messageService.repository;

import com.chatsystem.messageService.entity.DetailMessage;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DetailMessageRepository extends MongoRepository<DetailMessage, String> {
    @Aggregation(pipeline = {
            "{ $match: { 'message.timestamp': { $gte: ?0, $lte: ?1 }, 'sender.displayName': { $in: ?2 } } }",
            "{ $group: { _id: '$sender.displayName', totalMessages: { $sum: 1 } } }"
    })
    List<Map<String, Object>> countMessagesByTimeRangeAndUsers(long startDate, long endDate, String displayName);
}
