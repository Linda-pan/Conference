package com.elin4it.ssm.mybatis.pagination;

import java.io.Serializable;
import java.util.List;

public class PageList<T> implements Serializable {

	private static final long serialVersionUID = -7973223991185567338L;
	
	private int pageNo;
	private int pageSize;
	private int totalCount;
	private List<T> result;
	
	private int totalPage;	
	
	private void calTotalPage(){
		
		if (pageSize<0){
			pageSize = 0;
		}
		
		if (totalCount<0){
			totalCount = 0;
		}
		
		if (pageSize==0){
			totalPage = 0;
		} else {			
			totalPage = (totalCount%pageSize>0) ? totalCount / pageSize + 1 : totalCount / pageSize;
		}	
		
		if (pageNo>totalPage){
			pageNo = totalPage;
		}
		
		if (pageNo<1){
			pageNo = 1;
		}
	}
	
	public PageList(){
		this(1, 0, 0, null);
	}
	
	public PageList(int pageNo, int pageSize){
		this(pageNo, pageSize, 0, null);
	}
	
	public PageList(int pageNo, int pageSize, int totalCount){
		this(pageNo, pageSize, totalCount, null);
	}
	
	public PageList(int pageNo, int pageSize, int totalCount, List<T> result){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.result = result;
		
		calTotalPage();
	}

	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		calTotalPage();
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		calTotalPage();
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
		calTotalPage();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getNextPage() {
		return (isHasNext() ? pageNo + 1 : pageNo);
	}

	public int getPrePage() {
		return (isHasPre() ? pageNo - 1 : 0);
	}

	public boolean isHasNext() {
		return pageNo < totalPage;
	}

	public boolean isHasPre() {
		return pageNo > 1;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
