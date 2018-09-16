package com.st.rbac.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.st.rbac.pojo.Access;
import com.st.rbac.pojo.User;
import com.st.rbac.service.IAccessService;
import com.st.rbac.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService iUserService;
	@Autowired 
	private IAccessService iAccessService;
	
	@RequestMapping("/login.action")
	public ModelAndView login(User user, HttpSession session) {
		
		ModelAndView mView = new ModelAndView();
		
		User loginUser = iUserService.login(user);
		
		if (loginUser == null) {
			mView.addObject("error", "��¼ʧ��");
			mView.setViewName("login");
		} else {
			session.setAttribute("user", loginUser);
			
			// �û���¼�ɹ�֮��,
			// �����Ӧ��ɫ����Ȩ��ȫ��ȡ��, ����Session��
			
			Map<String, String> accesses = iAccessService.getByRoleid(loginUser.getRole().getRoleid());
			
			session.setAttribute("access", accesses);
			
			mView.setViewName("redirect:../view/index.action");
		}
		return mView;
	}
}
