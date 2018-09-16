package com.st.rbac.service;

import java.util.List;

import com.st.rbac.pojo.Menu;

public interface IMenuService {
	List<Menu> getMenu(Integer roleid);
}
