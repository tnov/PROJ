package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstHierarchy extends CommonEntity {
	private String hierarchyId = null;
	private String hierarchyName = null;
	private String hierarchyOrder = null;
	public String getHierarchyId() {
		return hierarchyId;
	}
	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	public String getHierarchyName() {
		return hierarchyName;
	}
	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}
	public String getHierarchyOrder() {
		return hierarchyOrder;
	}
	public void setHierarchyOrder(String hierarchyOrder) {
		this.hierarchyOrder = hierarchyOrder;
	}
}
