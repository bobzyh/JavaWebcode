package com.st.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.rbac.dao.PicMapper;
import com.st.rbac.service.IPicService;

@Service
public class PicServiceImpl implements IPicService{

	@Autowired
	private PicMapper picMapper;
	
	@Override
	public int addBatch(String[] pics, Integer pid) {
		return picMapper.insertBatch(pics, pid);
	}

}
