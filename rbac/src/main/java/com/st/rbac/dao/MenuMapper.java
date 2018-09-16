package com.st.rbac.dao;

import java.util.List;

import com.st.rbac.pojo.Menu;

public interface MenuMapper {
	List<Menu> selectByRoleid(Integer roleid);
}
