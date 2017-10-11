package com.jyzq.websocket;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import com.jyzq.entity.UserEntity;
import com.jyzq.service.UserService;

/**
 */
public class STOMPConnectEventListener  implements ApplicationListener<SessionConnectEvent> {

    @Autowired
    SocketSessionRegistry socketSessionRegistry;
    
	@Resource
	private UserService userService;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
    	StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
    	//login get from browser
        String openId = sha.getNativeHeader("openId").get(0);//agentId should be openid passed from font-end
        String sessionId = sha.getSessionId();
        System.out.println("openId=" + openId + ", sessionId="+sessionId);
        try {
            UserEntity user = new UserEntity();
            user.setOpenId(openId);
            user.setWsSessionId(sessionId);
			userService.syncSessionId(user);
		} catch (Exception e) {
	        System.out.println("syncSessionId error:"+e.getMessage());
			e.printStackTrace();
		}
        
        socketSessionRegistry.registerSessionId(openId, sessionId);
    }
}