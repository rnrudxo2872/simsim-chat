package com.kucom.simsim;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
@ServerEndpoint(value = "/SimChat")
public class ChatController {
	
	private static Logger logger = LoggerFactory.getLogger(ChatController.class);
	private static final List<Session> sessionList = new ArrayList<Session>();
	
	public ChatController() {
		logger.info("----------------채팅 클래스 ChatController 생성.-----------");
	}
	
	public void sendMessageToAllUsers(Session originUser, String sender, String msg) {
		try {
			for(Session session : ChatController.sessionList) {
				if(session.getId() != originUser.getId()) {
					session.getBasicRemote().sendText(sender + " : " + msg);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("onOpen -> connected : " + session.getId());
		
		try {
			session.getBasicRemote().sendText("대화방 연결");
			sessionList.add(session);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void onMessage(String getMsg, Session session) {
		logger.info("메세지 보냄! (ChatController - onMessage) ==> " + getMsg);
		
		String[] msgSplit = getMsg.split(getMsg);
		String sender = msgSplit[0];
		String msg = msgSplit[1];
		
		try {
			session.getBasicRemote().sendText("|me| " + msg);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		sendMessageToAllUsers(session, sender, msg);
	}
	
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		logger.info("Session => " + session.getId() + " has ended");
		sessionList.remove(session);
	}
}
