package application.emp0001.list;

import java.util.List;

import application.CommonForm;
import application.emp0001.Emp0001DataBean;

public class Emp0001LstForm extends CommonForm {
	private String employeeId = null;
	private String employeeName = null;
	private String sex = null;
	private String joinedYmd = null;
	private String retiredYmd = null;
	private String departmentId = null;
	private List<Emp0001DataBean> resultList = null;

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getJoinedYmd() {
		return joinedYmd;
	}
	public void setJoinedYmd(String joinedYmd) {
		this.joinedYmd = joinedYmd;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public List<Emp0001DataBean> getResultList() {
		return resultList;
	}
	public void setResultList(List<Emp0001DataBean> resultList) {
		this.resultList = resultList;
	}
	public String getRetiredYmd() {
		return retiredYmd;
	}
	public void setRetiredYmd(String retiredYmd) {
		this.retiredYmd = retiredYmd;
	}
}
