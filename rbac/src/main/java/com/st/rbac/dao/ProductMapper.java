package com.st.rbac.dao;

import java.util.List;

import com.st.rbac.pojo.Product;

public interface ProductMapper {
	List<Product> select();

	int insert(Product product);

	Product selectById(Integer pid);
}
