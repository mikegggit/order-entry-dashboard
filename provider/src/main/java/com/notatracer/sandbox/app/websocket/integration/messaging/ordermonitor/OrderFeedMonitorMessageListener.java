package com.notatracer.sandbox.app.websocket.integration.messaging.ordermonitor;

import com.notatracer.sandbox.app.websocket.integration.messaging.MessageListener;

public interface OrderFeedMonitorMessageListener extends MessageListener {

	public void onPortMaint(SOrderFeedMonPortMaint msg);
	
	public void onPortActivity(SOrderFeedMonPortActivity msg);
	
	public void onPortConnectionEvent(SOrderFeedMonPortConnectionEvent msg);

}
