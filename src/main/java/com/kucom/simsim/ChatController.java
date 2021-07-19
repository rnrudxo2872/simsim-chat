package com.kucom.simsim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kucom.simsim.domain.chatDTO;

@ServerEndpoint(value = "/SimChat")
public class ChatController {

	private static Logger logger = LoggerFactory.getLogger(ChatController.class);
	private static final List<Session> sessionList = new ArrayList<Session>();

	public ChatController() {
		logger.info("---------------- 객체 생성 ChatController .-----------");
	}

	public void sendMessageToAllUsers(Session originUser, String sender, String msg) {
		try {
			chatDTO chatDTO = insertChatDTO(sender, msg);
			chatDTO.setStatus(2);
			
			JSONObject messageObject = getMessageObject(chatDTO);
			for (Session session : ChatController.sessionList) {
				if (session.getId() != originUser.getId()) {
					session.getBasicRemote().sendText(messageObject.toJSONString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private chatDTO insertChatDTO (String sender, String message) {
		chatDTO chatDTO = new chatDTO();
		chatDTO.setSender(sender);
		chatDTO.setMessage(message);
		
		return chatDTO;
	}
	
	public JSONObject getMessageObject(chatDTO chatDTO) {
		HashMap<String, String> additionalDetail = new HashMap<String, String>();
		additionalDetail.put("sender", chatDTO.getSender());
		additionalDetail.put("message", chatDTO.getMessage());
		additionalDetail.put("status", String.valueOf(chatDTO.getStatus()));

		JSONObject messageObj = new JSONObject(additionalDetail);
		return messageObj;
	}
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("onOpen -> connected : " + session.getId());

		try {
			chatDTO chatDTO = insertChatDTO(session.getId(), "님이 입장하셨습니다.");
			chatDTO.setStatus(0);
			
			JSONObject messageObject = getMessageObject(chatDTO);
			session.getBasicRemote().sendText(messageObject.toJSONString());
			sessionList.add(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnMessage
	public void onMessage(String getMsg, Session session) {
		logger.info("메세지 받음! (ChatController - onMessage) ==> " + getMsg);

		String[] msgSplit = getMsg.split(",");
		String sender = msgSplit[0];
		String msg = msgSplit[1];

		try {
			chatDTO chatDTO = insertChatDTO("나", msg);
			chatDTO.setStatus(1);
			
			JSONObject messageObject = getMessageObject(chatDTO);
			session.getBasicRemote().sendText(messageObject.toJSONString());
		} catch (Exception e) {
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
