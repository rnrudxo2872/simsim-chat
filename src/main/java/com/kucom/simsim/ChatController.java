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
		logger.info("----------------ä�� Ŭ���� ChatController ����.-----------");
	}
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("onOpen -> connected : " + session.getId());
		
		try {
			session.getBasicRemote().sendText("��ȭ�� ����");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
