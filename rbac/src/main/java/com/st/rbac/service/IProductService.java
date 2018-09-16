package com.st.rbac.service;

import java.util.List;

import com.st.rbac.pojo.Product;

public interface IProductService {
	List<Product> select();

	int add(Product product);

	Product getById(Integer pid);
}
