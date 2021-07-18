package com.kucom.simsim;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint(value = "/SimChat")
public class ChatController {
	
	private Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	public ChatController() {
		logger.info("----------------채팅 클래스 ChatController 생성.-----------");
	}
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("onOpen -> connected : " + session.getId());
		
		try {
			session.getBasicRemote().sendText("대화방 연결");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
