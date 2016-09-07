package com.omt.wsserver.spring;


import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.omt.webservice.voting.entity.WebVotes;
import com.omt.wsserver.spring.vo.CalcInput;
import com.omt.wsserver.spring.vo.Result;

/**
 * WSS Socket Subscription 
 * @author tonyliu
 *
 */

@Controller
public class WssController {
	
	public static SimpMessagingTemplate simpMessagingTemplate;	
	
	@MessageMapping("/voting")
    @SendTo("/list/showResult")
    public Result voting(CalcInput input) throws Exception {
		Result message = new Result(input.getNum1()+"+"+input.getNum2()+"="+(input.getNum1()+input.getNum2()));
        return message;
	}
	
	public static void broadcast(List<WebVotes> list){
		simpMessagingTemplate.convertAndSend("/list/showResult", list);
	}
}
