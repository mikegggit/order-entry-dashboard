package com.notatracer.sandbox.app.websocket.integration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.notatracer.sandbox.app.websocket.integration.messaging.ordermonitor.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.notatracer.sandbox.app.websocket.integration.messaging.MessageUtils;
import com.notatracer.sandbox.app.websocket.integration.messaging.Message;
import com.notatracer.sandbox.app.websocket.integration.messaging.MessageListener;
import com.notatracer.sandbox.app.websocket.integration.messaging.MessageParser;
import com.notatracer.sandbox.app.websocket.integration.messaging.ordermonitor.SOrderFeedMonMsg;

@Component
public class DummyDataGeneratingDropConnection implements DropConnection {

	private static final long INTERVAL_MSGGEN_MILLI = 1000l;

	private static Logger LOGGER = Logger.getLogger(DummyDataGeneratingDropConnection.class);

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	private static Map<String, Set<String>> portFirmMap = new HashMap<>();
	
	@Autowired
	private MessageParser<SOrderFeedMonMsg> parser;

 	private SOrderFeedMonPortActivity sOrderFeedMonPortActivity = new SOrderFeedMonPortActivity();
	
	private SOrderFeedMonPortConnectionEvent sOrderFeedMonPortConnectionEvent = new SOrderFeedMonPortConnectionEvent();
	
	private SOrderFeedMonPortMaint sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
	
	private Map<String, SOrderFeedMonPortMaint> portMap = new HashMap();
	
	private MessageListener l;

	private ScheduledFuture<?> generatorHandle;

	private Runnable messageGenerator = () -> {

		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("Generating message.");
		}
		
		// Randomly generate port connection event
		double ratio = ThreadLocalRandom.current().nextDouble();
		if (ratio > .9d) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(String.format("Generating message [type=%s]", (char)Message.MsgType.PORT_CONNECTION_EVENT.getVal()));
			}
			
			sOrderFeedMonPortConnectionEvent.clear();
			 
			SOrderFeedMonPortMaint port = portMap.values().toArray(new SOrderFeedMonPortMaint[0])[ThreadLocalRandom.current().nextInt(portMap.size())];
			MessageUtils.setString(sOrderFeedMonPortConnectionEvent.groupName, port.groupName);
			MessageUtils.setString(sOrderFeedMonPortConnectionEvent.ringName, port.ringName);
			MessageUtils.setString(sOrderFeedMonPortConnectionEvent.portName, port.name);
			sOrderFeedMonPortConnectionEvent.event = Message.ConnectionEvent.CONNECT.getVal();
			MessageUtils.setTime(sOrderFeedMonPortConnectionEvent.time);
			
			this.onData(sOrderFeedMonPortConnectionEvent);
			
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(String.format("Generating message [type=%s]", (char)Message.MsgType.PORT_ACTIVITY.getVal()));
			}

			sOrderFeedMonPortActivity.clear();
			
			ThreadLocalRandom.current().nextInt(portMap.size());
			String port = portMap.keySet().toArray(new String[0])[ThreadLocalRandom.current().nextInt(portMap.size())];
			SOrderFeedMonPortMaint portMaint = portMap.get(port);
			
			MessageUtils.setTime(sOrderFeedMonPortActivity.last);
			MessageUtils.setString(sOrderFeedMonPortActivity.groupName, portMaint.groupName);
			MessageUtils.setString(sOrderFeedMonPortActivity.name, portMaint.name);
			MessageUtils.setString(sOrderFeedMonPortActivity.ringName, portMaint.ringName);
			
			MessageUtils.setLong(sOrderFeedMonPortActivity, "limitCurrent", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "limit1Min", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "limit5Min", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "blocks", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "numBlocks", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "numQuotes", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "numPurges", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "numUndPurges", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "ql1Min", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "qlCurrent", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "rateCurrent", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "rate1Min", 1000);
			MessageUtils.setLong(sOrderFeedMonPortActivity, "rate5Min", 1000);
			
			this.onData(sOrderFeedMonPortActivity);
		}
	};

	@Override
	public void connect(MessageListener l) {
		this.connect(l, null);
	}

	@Override
	public void connect(MessageListener l, String url) {

		LOGGER.info("Connecting.");
		
		if (!OrderFeedMonitorMessageListener.class.isAssignableFrom(l.getClass())) {
			throw new IllegalArgumentException("Unsupported listener class [class=" + l.getClass().getSimpleName() + ", required=OrderFeedMonitorMessageListener]");
		}
		this.l = l;
		
		sendPorts();
		generateEventStream();
	}

	private void generateEventStream() {
		LOGGER.info("Generating event stream.");
		generatorHandle = executor.scheduleAtFixedRate(messageGenerator, 0l, INTERVAL_MSGGEN_MILLI, TimeUnit.MILLISECONDS);
	}

	private void sendPorts() {
		sOrderFeedMonPortMaint.clear();

		
		sOrderFeedMonPortMaint.id = 1l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRABC1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRABC1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		
		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		
		sOrderFeedMonPortMaint.id = 2l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRABC2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 3l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRABC3");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRABC3", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 4l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRABC4");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRABC4", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 5l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "POP1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRPOP1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRPOP1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 6l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "POP1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRPOP2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRPOP2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 7l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DEF1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDEF1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDEF1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 8l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DEF1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDEF2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDEF2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 9l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DEF2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRSG21");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRSG21", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 10l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DEF2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRSG22");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRSG22", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 11l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "XYZ7");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRXYZ1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRXYZ1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 12l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "XYZ7");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRXYZ2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRXYZ2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 13l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "XYZ7");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRXYZ3");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRXYZ3", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 14l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "XYZ7");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRXYZ4");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRXYZ4", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 15l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "XYZ7");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRXYZ5");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRXYZ5", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 16l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "XYZ7");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRXYZ6");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRXYZ6", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);
		

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 17l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRABC1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRABC1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 18l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRABC2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 19l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRAC21");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRAC21", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 20l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ABC2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRAC22");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRAC22", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 21l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRORL1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRORL1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 22l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRORL2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRORL2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 23l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRORL3");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRORL3", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 24l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRORL4");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRORL4", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 25l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRORL5");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRORL5", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 26l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "ORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRORL6");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRORL6", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);


		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 27l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDUD1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDUD1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 28l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDUD2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDUD2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 29l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDUD3");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDUD3", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 30l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDUD4");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDUD4", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 31l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDUD5");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDUD5", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 32l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "DUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRDUD6");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRDUD6", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);



		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 33l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "MTG1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRMTG1");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRMTG1", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 34l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "MTG1");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRMTG2");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRMTG2", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 35l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "MTG2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRMG21");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRMG21", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 36l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "MTG2");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRMG22");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRMG22", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 37l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "MTG3");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRMG31");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRMG31", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);

		sOrderFeedMonPortMaint = new SOrderFeedMonPortMaint();
		sOrderFeedMonPortMaint.id = 38l;
		MessageUtils.setString(sOrderFeedMonPortMaint.groupName, "MTG3");
		MessageUtils.setString(sOrderFeedMonPortMaint.name, "CRMG32");
		MessageUtils.setString(sOrderFeedMonPortMaint.ringName, "FOO");
		portMap.put("CRMG32", sOrderFeedMonPortMaint);
		this.onData(sOrderFeedMonPortMaint);
	}

	@Override
	public void disconnect() {
		LOGGER.info("Disconnecting.");

		generatorHandle.cancel(true);
		
		this.l = null;
	}

	@Override
	public void onData(Object o) {
		
		if (!SOrderFeedMonMsg.class.isAssignableFrom(o.getClass())) {
			throw new IllegalArgumentException("Message must extend from SOrderFeedMonMsg [actual=" + o.getClass().getSimpleName() + "]");
		}
		
		this.parser.parse((SOrderFeedMonMsg)o, this.l);
	}

}
