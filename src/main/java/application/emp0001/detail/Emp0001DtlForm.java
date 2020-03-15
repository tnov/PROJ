package application.emp0001.detail;

import java.util.HashMap;

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
	private String tel = null;
	private String joinedYmd = null;
	private String retireYmd = null;
	private String authorized = null;
	private String authorizedChk = null;
	private String departmentId = null;
	private String deleteFlg = null;
	private String create_module_id = null;
	private String create_user_id = null;
	private String update_module_id = null;
	private String update_user_id = null;

	private HashMap<String, String> departmentMap = new HashMap<String, String>();


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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getAuthorizedChk() {
		return authorizedChk;
	}
	public void setAuthorizedChk(String authorizedChk) {
		this.authorizedChk = authorizedChk;
	}
	public String getBackMode() {
		return backMode;
	}
	public void setBackMode(String backMode) {
		this.backMode = backMode;
	}
	public HashMap<String, String> getDepartmentMap() {
		return departmentMap;
	}
	public void setDepartmentMap(HashMap<String, String> departmentMap) {
		this.departmentMap = departmentMap;
	}
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public String getCreate_module_id() {
		return create_module_id;
	}
	public void setCreate_module_id(String create_module_id) {
		this.create_module_id = create_module_id;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getUpdate_module_id() {
		return update_module_id;
	}
	public void setUpdate_module_id(String update_module_id) {
		this.update_module_id = update_module_id;
	}
	public String getUpdate_user_id() {
		return update_user_id;
	}
	public void setUpdate_user_id(String update_user_id) {
		this.update_user_id = update_user_id;
	}
}
