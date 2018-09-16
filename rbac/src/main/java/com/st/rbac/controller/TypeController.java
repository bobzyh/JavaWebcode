package com.st.rbac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.st.rbac.pojo.Type;
import com.st.rbac.service.ITypeService;

@Controller
public class TypeController {

	@Autowired
	private ITypeService iTypeService;
	
	@RequestMapping("/type/getAll.action")
	@ResponseBody
	public List<Type> getAll(){
		return iTypeService.getAll();
	}
}
