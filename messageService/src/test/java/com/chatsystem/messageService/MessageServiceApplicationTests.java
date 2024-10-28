package com.chatsystem.messageService;

import com.chatsystem.messageService.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
class MessageServiceApplicationTests {

	@Autowired
	ReportService reportService;
	@Test
	void contextLoads() {
		reportService.testUnwind(Instant.parse("2024-09-30T00:00:00Z"), Instant.parse("2024-10-31T23:59:59Z"));
	}

}
