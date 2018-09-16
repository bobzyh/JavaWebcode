package com.st.rbac.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.st.rbac.pojo.SocketMessage;
import com.st.rbac.pojo.User;

/*
 * ����ͨ��WebSocket�����ı����ݵĴ�����
 */
public class MessageSocket extends TextWebSocketHandler{
	
	private List<WebSocketSession> sockets = new ArrayList<>();
	
	
	/**
	 * ���ͻ������������������ʱ��Ӧ
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("���ӳɹ�");
		
		// ��ͻ��˷�����Ϣ 
		TextMessage msg = new TextMessage("�������ɹ�������������");
		session.sendMessage(msg);
		
		
		sockets.add(session);
	
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("���ӹر�");
		
		// �Ͽ�ʱ, ��Ҫ���б����Ƴ�
		sockets.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//  ���տͻ��˷��͵�����
		System.out.println("�յ�:" + message.getPayload());
	}
	
	public void sendMsg(SocketMessage msg) throws IOException {
		
		// ����Ϣ����, תΪJSON�ַ���
		ObjectMapper mapper = new ObjectMapper();
		String msgJSON = mapper.writeValueAsString(msg);
		
		// ��ͻ��˷�����Ϣ
		TextMessage txtMsg = new TextMessage(msgJSON);
		
		// ���˵�һ�����û�  
		for (WebSocketSession session : sockets) {
			// session.getAttributes()��ȡ���ľ������������е�atts
			User user = (User) session.getAttributes().get("user");
			
			if (user.getRole().getRoleid() == 3) {
				session.sendMessage(txtMsg);
			}
		}
	}
}
