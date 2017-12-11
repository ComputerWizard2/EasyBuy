package com.yz.bean;

import java.util.List;

public class Page {
	private int pageSize;// һҳ������
	private int currentPage;// ��ǰ��ҳ
	private int dataNum;// ���ݱ��е�����
	private int pageCount;// ��ҳ��
	private List<UserBean> list;// һҳ������������

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
		this.pageCount = dataNum % pageSize == 0 ? dataNum / pageSize : dataNum / pageSize + 1;
	}

	public List<UserBean> getList() {
		return list;
	}

	public void setList(List<UserBean> list) {
		this.list = list;
	}

}
