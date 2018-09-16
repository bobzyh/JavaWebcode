package com.st.rbac.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.rbac.dao.AccessMapper;
import com.st.rbac.pojo.Access;
import com.st.rbac.service.IAccessService;

@Service
public class AccessServiceImpl implements IAccessService{

	@Autowired
	private AccessMapper accessMapper;
	
	@Override
	public Map<String, String> getByRoleid(Integer roleid) {
		List<Access> accesses = accessMapper.selectByRoleid(roleid);
		
		// 通过List 生成一个Map
		Map<String, String> map = new HashMap<>();
		
		for (Access access : accesses) {
			
			map.put(access.getUrl(), access.getUrlname());
		}
		
		
		return map;
	}

}
