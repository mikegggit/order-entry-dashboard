package com.notatracer.sandbox.app.websocket.integration.emulation;

import java.util.ArrayList;
import java.util.List;

import com.notatracer.sandbox.app.websocket.integration.messaging.ordermonitor.OrderFeedMonitorMessageListener;

/**
 * Generates a stream of messages emulating a connection to the Order Feed.
 * @author grudkowm
 *
 */
public class OrderFeedStreamGenerator implements StreamGenerator {

	private OrderFeedMonitorMessageListener listener;

	public OrderFeedStreamGenerator(OrderFeedMonitorMessageListener l) {
		this.listener = l;
	}

	@Override
	public void start() {
		loadScript();
		
	}

	private void loadScript() {
		List<String> script = new ArrayList();
//		script.add(Message.MsgType.PORT_MAINT + "|" + 
		
	}
	
	
}
