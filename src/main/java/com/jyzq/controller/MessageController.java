package com.jyzq.controller;

import javax.annotation.Resource;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jyzq.bean.IncomeMessage;
import com.jyzq.bean.OutcomeMessage;
import com.jyzq.entity.UserEntity;
import com.jyzq.service.MessageService;
import com.jyzq.service.UserService;
import com.jyzq.websocket.SocketSessionRegistry;

@Controller
public class MessageController {
	@Resource
	private UserService userService;
	
	@Resource
	private MessageService messageService;
	
	@Resource
    private SimpMessagingTemplate messagingTemplate;
    
	@Resource
    private SocketSessionRegistry socketSessionRegistry;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="phone", required=false, defaultValue="0") String phone, 
    		@RequestParam(value="userId", required=false, defaultValue="") String userId, Model model) throws Exception {
    	
    	try {
        	model.addAttribute("phone", phone);
        	model.addAttribute("userId", userId);
        	
        	UserEntity user = new UserEntity();
        	user.setUserId(Integer.parseInt(userId));
            
            this.userService.getUser(user);
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
        
        return "greeting";
    }
    
    
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OutcomeMessage greeting(IncomeMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay      
        return new OutcomeMessage("Hello, " + message.getOpenId() + "!");
    }
    
    /* This method can send msg to specified user
     * 
     * */
    @MessageMapping("/msg")
    public void sendMsgToUser(IncomeMessage message) throws Exception{
    	System.out.println("sendMsgToUsern start");
    	String destOpenId = message.getDestOpenId();
    	String sessionId = socketSessionRegistry.getSessionIds(destOpenId).stream().findFirst().get();

    	System.out.println("destOpenId=" + destOpenId + ", sessionId="+sessionId+", content="+message.getContent());
    	messageService.sendMessage(message.getOpenId(), message.getDestOpenId(), message.getContent());;
    	messagingTemplate.convertAndSendToUser(sessionId, "/topic/msg", 
    		new OutcomeMessage(message.getContent()),
    		createHeaders(sessionId));
    }
    
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
