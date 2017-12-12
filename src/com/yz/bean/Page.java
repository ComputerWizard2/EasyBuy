package com.yz.bean;

import java.util.List;

public class Page<T> {
	private int pageSize;// һҳ������
	private int currentPage;// ��ǰ��ҳ
	private int dataNum;// ���ݱ��е�����
	private int pageCount;// ��ҳ��
	private List<T> list;// һҳ������������

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
		// ��ҳ��
		this.pageCount = pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
