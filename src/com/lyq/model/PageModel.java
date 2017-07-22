package com.lyq.model;

import java.util.List;

public class PageModel<T> {
	private int totalRecords; //总记录数
	private List<T> list;//结果集
	private int pageNo;//当前页
	private int pageSize;//每页显示条数

	//获取第一页
	public int getToPageNo(){
		return 1;
	}
	
	//获得上一页
	public int getNextPage(){
		if(pageNo<=1){
			return 1;
		}
		return pageNo-1;
	}
	
	// 获取下一页
	public int getNextPageNo(){
		if(pageNo>=getTotalPages()){
			return getTotalPages() == 0 ? 1: getTotalPages() ;
		}
		return pageNo +1;
	}
	
	//获取取得最后一页
	public int getBottomPageNo(){
		return  getTotalPages() ==0 ? 1 : getTotalPages(); 
	} 
	
	
	// 取得总页数
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	
	
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
