package com.st.rbac.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.st.rbac.pojo.User;

public class SocketInterceptor implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attrs) throws Exception {

		// ��ȡHttpSession�д�ŵ��û���¼��Ϣ
		/**
		 * �����Ƿ��� ��Ķ���, 
		 */
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			
			/*
			 * getSession(); ��ȡSession����, ���û��Session����, ��ô�ʹ���һ��.
			 * getSession(boolean); ����trueʱ, ����ͬ��
			 * 						����falseʱ, �����Session����, ��ȡ�������, ���û��Session,����null
			 */
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			
			if (session != null) {
				User loginUser = (User) session.getAttribute("user");
				
				/*
				 * ��ӵ�attrs�е�����, �ᱻ���ݵ�WebSocketSession������
				 */
				attrs.put("user", loginUser);
			}
			
		}
		return true;
	}

}
