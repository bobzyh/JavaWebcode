package com.st.rbac.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.st.rbac.pojo.Menu;
import com.st.rbac.pojo.User;
import com.st.rbac.service.IMenuService;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	private IMenuService iMenuService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/getmenu")
	@ResponseBody
	public List<Menu> getmenu(HttpSession session){
		
		// 取当前登录用户
		User user = (User) session.getAttribute("user");
		
		List<Menu> menus = iMenuService.getMenu(user.getRole().getRoleid());
		return menus;
	}
}
