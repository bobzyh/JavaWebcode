package com.st.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.rbac.dao.MenuMapper;
import com.st.rbac.pojo.Menu;
import com.st.rbac.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getMenu(Integer roleid) {
		return menuMapper.selectByRoleid(roleid);
	}
}
