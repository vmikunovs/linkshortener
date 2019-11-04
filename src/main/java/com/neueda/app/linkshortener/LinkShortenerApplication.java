package com.neueda.app.linkshortener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinkShortenerApplication {
	private static Logger log = LoggerFactory.getLogger(LinkShortenerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LinkShortenerApplication.class, args);

		log.info("");
		log.info("");
		log.info("=================================");
		log.info("          SERVER STARTED");
		log.info("=================================");
		log.info("");
	}

}
