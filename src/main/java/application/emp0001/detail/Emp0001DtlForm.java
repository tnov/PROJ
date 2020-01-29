package application.emp0001.detail;

import application.CommonForm;

public class Emp0001DtlForm extends CommonForm {

	private String backMode = null;

	private String mode = null;

	private String paramEmployeeId = null;
	private String employeeId = null;
	private String employeeName = null;
	private String birthYmd = null;
	private String sex = null;
	private String zipCode = null;
	private String address = null;
	private String joinedYmd = null;
	private String retireYmd = null;
	private String departmentId = null;
	private String authorized = null;

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

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getBirthYmd() {
		return birthYmd;
	}
	public void setBirthYmd(String birthYmd) {
		this.birthYmd = birthYmd;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRetireYmd() {
		return retireYmd;
	}
	public void setRetireYmd(String retireYmd) {
		this.retireYmd = retireYmd;
	}
	public String getAuthorized() {
		return authorized;
	}
	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}
	public String getBackMode() {
		return backMode;
	}
	public void setBackMode(String backMode) {
		this.backMode = backMode;
	}
}
