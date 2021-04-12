package application.database.entity;

import lib.database.entity.CommonEntity;

public class MstMenu extends CommonEntity {
	private String hierarchy = null;
	private String functionOrder = null;
	private String functionId = null;
	public String getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	public String getFunctionOrder() {
		return functionOrder;
	}
	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
}
