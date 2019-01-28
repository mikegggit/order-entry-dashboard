package com.notatracer.sandbox.app.websocket.web.controller;

import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

//    @MessageMapping("/foo") 
//    public String handle(String greeting) {
//        return "foo";
//    }
    
    @RequestMapping(value = "/")
	public String index() {
		return "index";
	}
    
    @SubscribeMapping("/orderfeed")
    public String orderfeed() {
    	return "inside orderfeed";
    }

    @SubscribeMapping("/topic/orderfeed")
    public String orderfeed2() {
    	System.out.println("orderfeed2");
    	return "inside orderfeed2";
    }

//    @SubscribeMapping("/app/topic")
    
}
