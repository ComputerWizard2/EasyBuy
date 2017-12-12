package com.yz.bean;

import java.util.List;

public class Page<T> {
	private int pageSize;// 一页的数量
	private int currentPage;// 当前的页
	private int dataNum;// 数据表中的数量
	private int pageCount;// 总页数
	private List<T> list;// 一页所包含的数据

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", currentPage=" + currentPage + ", dataNum=" + dataNum + ", pageCount="
				+ pageCount + ", list=" + list + "]";
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getDataNum() {
		return dataNum;
	}

	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		// 总页数
		this.pageCount = pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
