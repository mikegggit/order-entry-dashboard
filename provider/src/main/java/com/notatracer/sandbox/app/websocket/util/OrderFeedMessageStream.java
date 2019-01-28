package com.notatracer.sandbox.app.websocket.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderFeedMessageStream implements CommandLineRunner {

	private final SimpMessagingTemplate websocket;
	private Logger LOGGER = Logger.getLogger(OrderFeedMessageStream.class);
	
	@Autowired
	public OrderFeedMessageStream(SimpMessagingTemplate websocket) {
		this.websocket = websocket;
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("MessageGenerator::run");
		LOGGER.info("MessageGenerator::run");
		
		for (int i = 0; i < 10; i++) {
			this.websocket.convertAndSend(
					"/topic/orderfeed", "test" + i);
		}
	}

}
