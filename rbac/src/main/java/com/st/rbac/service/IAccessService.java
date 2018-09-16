package com.st.rbac.service;

import java.util.List;
import java.util.Map;

import com.st.rbac.pojo.Access;

public interface IAccessService {
	Map<String, String> getByRoleid(Integer roleid);
}
