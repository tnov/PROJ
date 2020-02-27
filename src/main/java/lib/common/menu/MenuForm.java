package lib.common.menu;

import java.util.List;

import lib.common.BaseForm;

public class MenuForm extends BaseForm {
	private List<HierarchyBean> hierarchyList;
	private String menuMovePath;
	public String getMenuMovePath() {
		return menuMovePath;
	}
	public void setMenuMovePath(String menuMovePath) {
		this.menuMovePath = menuMovePath;
	}
	public List<HierarchyBean> getHierarchyList() {
		return hierarchyList;
	}
	public void setHierarchyList(List<HierarchyBean> hierarchyList) {
		this.hierarchyList = hierarchyList;
	}
}
