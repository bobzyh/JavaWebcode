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
		
		// 1. 取出当前的请求地址      *.action
		String url = request.getRequestURI();
		// /rbac/role/product/addview.action
		url = url.substring(url.indexOf("role/"));
		
		// 2. 查询权限的匹配
		HttpSession session = request.getSession();
		Map<String, String> accesses =  (Map<String, String>) session.getAttribute("access");
		
		
		// 3. 如果有权限 放行
		//    如果没有权限  跳转到一个提示页面
		String urlname = accesses.get(url);
		
		if (urlname == null) {
			// 没有权限
			
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
