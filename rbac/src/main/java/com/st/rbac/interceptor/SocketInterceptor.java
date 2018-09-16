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

		// 获取HttpSession中存放的用户登录信息
		/**
		 * 对象是否是 类的对象, 
		 */
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			
			/*
			 * getSession(); 获取Session对象, 如果没有Session对象, 那么就创建一个.
			 * getSession(boolean); 传递true时, 操作同上
			 * 						传递false时, 如果有Session对象, 获取这个对象, 如果没有Session,返回null
			 */
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			
			if (session != null) {
				User loginUser = (User) session.getAttribute("user");
				
				/*
				 * 添加到attrs中的数据, 会被传递到WebSocketSession对象中
				 */
				attrs.put("user", loginUser);
			}
			
		}
		return true;
	}

}
