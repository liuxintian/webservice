package com.omt.wsserver.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagingApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    public MessagingApplicationListener(SimpMessagingTemplate messagingTemplate) {
        WssController.simpMessagingTemplate = messagingTemplate;
    }

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
