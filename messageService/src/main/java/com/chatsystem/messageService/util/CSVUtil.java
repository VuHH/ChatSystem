package com.chatsystem.messageService.util;

import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class CSVUtil {
  public static InputStreamResource convertToCsvFile(List<Map> data) {
    StringBuilder csvBuilder = new StringBuilder();

    // Lấy header từ Map keys
    if (data.isEmpty()) {
      return null;
    }
    Map<String, Object> headerMap = data.get(0);
    StringJoiner headerJoiner = new StringJoiner(",");
    headerMap.keySet().forEach(headerJoiner::add);
    csvBuilder.append(headerJoiner).append("\n");

    // Ghi dữ liệu từ Map values
    for (Map<String, Object> row : data) {
      StringJoiner rowJoiner = new StringJoiner(",");
      row.values().forEach(value -> rowJoiner.add(value != null ? value.toString() : ""));
      csvBuilder.append(rowJoiner).append("\n");
    }

    ByteArrayInputStream byteArrayInputStream =
        new ByteArrayInputStream(csvBuilder.toString().getBytes(StandardCharsets.UTF_8));
    return new InputStreamResource(byteArrayInputStream);
  }
}
