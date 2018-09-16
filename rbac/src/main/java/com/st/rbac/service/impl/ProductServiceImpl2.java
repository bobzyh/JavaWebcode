package com.st.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.rbac.dao.ProductMapper;
import com.st.rbac.pojo.Product;
import com.st.rbac.service.IProductService;

@Service
public class ProductServiceImpl2 implements IProductService{

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> select() {
		return productMapper.select();
	}

	@Override
	public int add(Product product) {
		return productMapper.insert(product);
	}

	@Override
	public Product getById(Integer pid) {
		return productMapper.selectById(pid);
	}

}
