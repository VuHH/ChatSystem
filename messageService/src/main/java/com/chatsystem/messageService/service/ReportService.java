package com.chatsystem.messageService.service;

import com.chatsystem.messageService.entity.DetailMessage;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
  private final MongoTemplate mongoTemplate;

    public ReportService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Map> getMessageCountByTimeRangeAndDisplayNames(
      Instant startDate, Instant endDate, String displayNames) {
    Aggregation aggregation =
        Aggregation.newAggregation(
            Aggregation.match(
                Criteria.where("message.timestamp")
                    .gte(startDate.toEpochMilli())
                    .lte(endDate.toEpochMilli())
                    .and("sender.displayName")
                    .in(displayNames)),
            Aggregation.group("sender.displayName").count().as("totalMessages"),
            Aggregation.sort(Sort.by(Sort.Direction.DESC, "totalMessages")));

    AggregationResults<Map> results =
        mongoTemplate.aggregate(aggregation, DetailMessage.class, Map.class);
    return results.getMappedResults();
  }

  public List<Map> getAttachmentCountByTimeRangeAndDisplayNames(
      Instant startDate, Instant endDate, String displayNames) {
    Aggregation aggregation =
        Aggregation.newAggregation(
            Aggregation.match(
                Criteria.where("message.timestamp")
                    .gte(startDate.toEpochMilli())
                    .lte(endDate.toEpochMilli())
                    .and("sender.displayName")
                    .in(displayNames)),
            Aggregation.unwind("message.attachments"),
            Aggregation.group("sender.displayName").count().as("totalAttachments"),
            Aggregation.sort(Sort.by(Sort.Direction.DESC, "numAttachments")));

    AggregationResults<Map> results =
        mongoTemplate.aggregate(aggregation, DetailMessage.class, Map.class);
    return results.getMappedResults();
  }

  public List<Map> testUnwind(Instant startDate, Instant endDate) {
    Aggregation aggregation =
        Aggregation.newAggregation(Aggregation.unwind("message.attachments", true));
    AggregationResults<Map> results =
        mongoTemplate.aggregate(aggregation, DetailMessage.class, Map.class);
    return results.getMappedResults();
  }

  public List<Map> getCustomMessageInfo(Instant startDate, Instant endDate) {

    Aggregation aggregation =
        Aggregation.newAggregation(
            Aggregation.match(
                Criteria.where("message.timestamp")
                    .gte(startDate.toEpochMilli())
                    .lte(endDate.toEpochMilli())),
            Aggregation.unwind("message.attachments", true), // Unwind attachments
            Aggregation.project()
                .and("message._id")
                .as("messageId")
                .and("roomId")
                .as("roomId")
                .and("sender.displayName")
                .as("senderName")
                .and("sender.phoneNumber")
                .as("senderPhoneNumber")
                .and(
                    ConditionalOperators.Cond.when(
                            ComparisonOperators.Eq.valueOf("roomType").equalTo("INDIVIDUAL"))
                        .thenValueOf("receiver.id")
                        .otherwise("roomName"))
                .as("recipient")
                .and("roomName")
                .as("roomName")
                .and("receiver.type")
                .as("roomType")
                .and("message.timestamp")
                .as("timestamp")
                .and(
                    ConditionalOperators.Cond.when(
                            ConditionalOperators.IfNull.ifNull("message.attachments")
                                .then(new ArrayList<>()))
                        .thenValueOf("message.attachments.name")
                        .otherwise("message.conversation"))
                .as("conversation"),
            Aggregation.sort(Sort.by(Sort.Direction.ASC, "message.timestamp")));

    AggregationResults<Map> results =
        mongoTemplate.aggregate(aggregation, DetailMessage.class, Map.class);
    return results.getMappedResults();
  }
}
