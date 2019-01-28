package com.notatracer.sandbox.app.websocket.integration.messaging.ordermonitor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.notatracer.sandbox.app.websocket.integration.messaging.MessageListener;
import com.notatracer.sandbox.app.websocket.integration.messaging.MessageParser;

@Component 
public class SOrderFeedMonParser implements MessageParser<SOrderFeedMonMsg> {

	private Logger logger = Logger.getLogger(SOrderFeedMonParser.class);
	
	@Override
	public void parse(SOrderFeedMonMsg msg, MessageListener l) {
		
		if (!OrderFeedMonitorMessageListener.class.isAssignableFrom(l.getClass())) {
			throw new IllegalArgumentException("Unsupported listener class [class=" + l.getClass().getSimpleName() + ", required=OrderFeedMonitorMessageListener]");
		}
		switch (msg.msgType) {
		
		case PORT_MAINT:
			logger.debug("parse::PORT_MAINT");
			((OrderFeedMonitorMessageListener)l).onPortMaint((SOrderFeedMonPortMaint)msg);
			break;
		case PORT_ACTIVITY:
			logger.debug("parse::PORT_ACTIVITY");
			((OrderFeedMonitorMessageListener)l).onPortActivity((SOrderFeedMonPortActivity)msg);
			break;
		case PORT_CONNECTION_EVENT:
			logger.debug("parse::PORT_CONNECTION_EVENT");
			((OrderFeedMonitorMessageListener)l).onPortConnectionEvent((SOrderFeedMonPortConnectionEvent)msg);
			break;
		default:
			break;
		}
		
		
	}

}
