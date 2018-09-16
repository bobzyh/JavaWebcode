package com.st.rbac.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.st.rbac.pojo.Access;
import com.st.rbac.pojo.User;

public class RoleInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. ȡ����ǰ�������ַ      *.action
		String url = request.getRequestURI();
		// /rbac/role/product/addview.action
		url = url.substring(url.indexOf("role/"));
		
		// 2. ��ѯȨ�޵�ƥ��
		HttpSession session = request.getSession();
		Map<String, String> accesses =  (Map<String, String>) session.getAttribute("access");
		
		
		// 3. �����Ȩ�� ����
		//    ���û��Ȩ��  ��ת��һ����ʾҳ��
		String urlname = accesses.get(url);
		
		if (urlname == null) {
			// û��Ȩ��
			
			response.sendRedirect("../../accesserror.jsp");
			
			return false;
		} 
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
