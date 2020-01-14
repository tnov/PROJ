package application.emp0001.detail;

import application.CommonForm;

public class Emp0001DtlForm extends CommonForm {
	private String paramEmployeeId = null;
	private String employeeId = null;
	private String employeeName = null;
	private String sex = null;
	private String joinedYmd = null;
	private String retiredYmd = null;
	private String departmentId = null;

	public String getParamEmployeeId() {
		return paramEmployeeId;
	}
	public void setParamEmployeeId(String paramEmployeeId) {
		this.paramEmployeeId = paramEmployeeId;
	}
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
	public String getRetiredYmd() {
		return retiredYmd;
	}
	public void setRetiredYmd(String retiredYmd) {
		this.retiredYmd = retiredYmd;
	}
}
