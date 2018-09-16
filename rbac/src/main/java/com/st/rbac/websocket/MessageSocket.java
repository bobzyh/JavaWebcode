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
 * 可以通过WebSocket处理文本内容的处理器
 */
public class MessageSocket extends TextWebSocketHandler{
	
	private List<WebSocketSession> sockets = new ArrayList<>();
	
	
	/**
	 * 当客户端与服务器建立连接时响应
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("连接成功");
		
		// 向客户端发送消息 
		TextMessage msg = new TextMessage("服务器成功接收连接请求");
		session.sendMessage(msg);
		
		
		sockets.add(session);
	
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("连接关闭");
		
		// 断开时, 需要从列表中移除
		sockets.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//  接收客户端发送的数据
		System.out.println("收到:" + message.getPayload());
	}
	
	public void sendMsg(SocketMessage msg) throws IOException {
		
		// 将消息内容, 转为JSON字符串
		ObjectMapper mapper = new ObjectMapper();
		String msgJSON = mapper.writeValueAsString(msg);
		
		// 向客户端发送消息
		TextMessage txtMsg = new TextMessage(msgJSON);
		
		// 过滤掉一部分用户  
		for (WebSocketSession session : sockets) {
			// session.getAttributes()获取到的就是在拦截器中的atts
			User user = (User) session.getAttributes().get("user");
			
			if (user.getRole().getRoleid() == 3) {
				session.sendMessage(txtMsg);
			}
		}
	}
}
