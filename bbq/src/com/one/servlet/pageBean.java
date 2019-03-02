package com.one.servlet;

public class pageBean {
	private int pageNum;
	private int pageSize;
	private int totalNum;
	
	private int totalPage;
	private int startIndex;
	
	private String[] list;
	
	private int start;
	private int end;
	public pageBean(int pageNum, int pageSize, int totalNum) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		
		if(totalNum%pageSize==0){
			this.totalPage = totalNum/pageSize;
		}else{
			this.totalPage = totalNum/pageSize + 1;
		}
		this.startIndex = (pageNum-1)*pageSize;
		
		this.start = 1;
		this.end = 5;
		
		if(totalPage<=5){
			this.end = this.totalPage;
		}else{
			this.start = pageNum - 2;
			this.end = pageNum + 2;
			
			if(start<0){
				this.start =1;
				this.end = 5;
			}
			if(end > this.totalPage){
				this.end = totalPage;
				this.start = end -5;
			}
		}
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String[] getList() {
		return list;
	}
	public void setList(String[] list) {
		this.list = list;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
}
