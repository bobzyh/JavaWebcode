package com.st.rbac.pojo;

import java.util.List;

public class Menu2 {
	private Integer mid;
	private String mname;
	private String murl;
	private Integer pmid;
	private List<Menu2> menus;
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	public Integer getPmid() {
		return pmid;
	}
	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}
	public List<Menu2> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu2> menus) {
		this.menus = menus;
	}
}
