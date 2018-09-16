package com.st.rbac.dao;

public interface PicMapper {
	int insertBatch(String[] pics, Integer pid);
}
