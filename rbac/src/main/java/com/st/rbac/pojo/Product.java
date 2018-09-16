package com.st.rbac.pojo;

import java.util.List;

public class Product {
	private Integer pid;
	private String pname;
	private Integer ptype;
	
	private List<Pic> picList;
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPtype() {
		return ptype;
	}
	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}
	public List<Pic> getPicList() {
		return picList;
	}
	public void setPicList(List<Pic> picList) {
		this.picList = picList;
	}
}
