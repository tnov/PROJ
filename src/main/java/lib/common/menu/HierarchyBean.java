package lib.common.menu;

import java.util.List;

public class HierarchyBean {
	private String hierarchyId;
	private String hierarchyName;
	private String dispFlg;
	private List<MenuBean> menuList;
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
	public List<MenuBean> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuBean> menuList) {
		this.menuList = menuList;
	}
	public String getDispFlg() {
		return dispFlg;
	}
	public void setDispFlg(String dispFlg) {
		this.dispFlg = dispFlg;
	}
}
