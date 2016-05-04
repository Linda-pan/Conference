package com.elin4it.ssm.mybatis.pagination;


import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageBounds<T> extends RowBounds implements Serializable {
	
	private static final long serialVersionUID = 8338096930461466386L;

	private int pageNo = 1;	
	private int pageSize = 0;	
	private List<Order> orders = null;
	
	private PageList<T> pageList;
	
	public PageBounds() {
	}
	
	public PageBounds(int pageNo, int pageSize) {
		this(pageNo, pageSize, new ArrayList<Order>());
	}

	public PageBounds(int pageNo, int pageSize, Order... order) {
		this(pageNo, pageSize, Arrays.asList(order));
	}

	public PageBounds(int pageNo, int pageSize, List<Order> orders) {
		this.pageNo = (pageNo<1) ? 1 : pageNo;
		this.pageSize= (pageSize<0) ? 0 : pageSize;
		this.orders = orders;
	}	
	
	@Override
	public int getLimit() {
		return pageSize;
	}

	@Override
	public int getOffset() {		
		return (pageNo - 1) * pageSize;		
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public PageList<T> getPageList() {
		return pageList;
	}

	public void setPageList(PageList<T> pageList) {
		this.pageList = pageList;
	}
}
