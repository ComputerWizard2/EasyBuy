package com.yz.bean;

public class ProducBean {
	private int epc_parent_id;

	private int epc_child_id;
	private String epc_parentName;
	private String epc_childName;

	@Override
	public String toString() {
		return "ProducBean [epc_parent_id=" + epc_parent_id + ", epc_child_id=" + epc_child_id + ", epc_parentName="
				+ epc_parentName + ", epc_childName=" + epc_childName + "]";
	}

	public int getEpc_parent_id() {
		return epc_parent_id;
	}

	public void setEpc_parent_id(int epc_parent_id) {
		this.epc_parent_id = epc_parent_id;
	}

	public int getEpc_child_id() {
		return epc_child_id;
	}

	public void setEpc_child_id(int epc_child_id) {
		this.epc_child_id = epc_child_id;
	}

	public String getEpc_parentName() {
		return epc_parentName;
	}

	public void setEpc_parentName(String epc_parentName) {
		this.epc_parentName = epc_parentName;
	}

	public String getEpc_childName() {
		return epc_childName;
	}

	public void setEpc_childName(String epc_childName) {
		this.epc_childName = epc_childName;
	}

}
