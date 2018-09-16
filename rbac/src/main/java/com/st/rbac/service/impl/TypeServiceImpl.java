package com.st.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.rbac.dao.TypeMapper;
import com.st.rbac.pojo.Type;
import com.st.rbac.service.ITypeService;

@Service
public class TypeServiceImpl implements ITypeService{

	@Autowired
	private TypeMapper typeMapper;
	
	@Override
	public List<Type> getAll() {
		return typeMapper.selectAll();
	}

	
}
