package com.chatsystem.messageService.controller;

import com.chatsystem.messageService.service.ReportService;
import com.chatsystem.messageService.util.CSVUtil;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

  private final ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/numb-text")
  public List<Map> getNumberOfTextMessage(
      @RequestParam Instant startTime, @RequestParam Instant endTime, @RequestParam String user) {
    return reportService.getMessageCountByTimeRangeAndDisplayNames(startTime, endTime, user);
  }

  @GetMapping("/numb-attach")
  public List<Map> getNumberOfAttach(
      @RequestParam Instant startTime, @RequestParam Instant endTime, @RequestParam String user) {
    return reportService.getAttachmentCountByTimeRangeAndDisplayNames(startTime, endTime, user);
  }

  @GetMapping("/csv")
  public ResponseEntity<InputStreamResource> getCSVFile(
      @RequestParam Instant startTime, @RequestParam Instant endTime) {
    InputStreamResource resource =
        CSVUtil.convertToCsvFile(reportService.getCustomMessageInfo(startTime, endTime));
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=messages.csv")
        .contentType(MediaType.parseMediaType("text/csv"))
        .body(resource);
  }
}
