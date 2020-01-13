package lib.common.menu;

import java.util.List;

import lib.common.BaseForm;

public class MenuForm extends BaseForm {
	private List<MenuBean> list;
	private String menuMovePath;
	public String getMenuMovePath() {
		return menuMovePath;
	}
	public void setMenuMovePath(String menuMovePath) {
		this.menuMovePath = menuMovePath;
	}
	public List<MenuBean> getList() {
		return list;
	}
	public void setList(List<MenuBean> list) {
		this.list = list;
	}
}
