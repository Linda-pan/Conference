package com.elin4it.ssm.utils;


import net.sf.json.JSONObject;

public class Grid {
	private int total;
	private Object rows;
	public Grid(int total, Object list) {
		this.total = total;
		this.rows = list;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	public String toJSONString(){
		return JSONObject.fromObject(this).toString();
	}
}

