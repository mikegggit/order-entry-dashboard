package com.notatracer.sandbox.app.websocket.integration.messaging.ordermonitor;

import java.util.Arrays;

public class SOrderFeedMonPortMaint extends SOrderFeedMonMsg {

	public byte[] name = new byte[6];
	public byte[] groupName = new byte[4];
	public byte[] ringName = new byte[4]; 
	public long id;
	
	public SOrderFeedMonPortMaint() {
		super();
		msgType = MsgType.PORT_MAINT;
		this.clear();
	}
	
	@Override
	public void clear() {
		Arrays.fill(this.name, (byte) ' ');
		Arrays.fill(this.groupName, (byte) ' ');
		Arrays.fill(this.ringName, (byte) ' ');
		this.id = 0;
	}

}
