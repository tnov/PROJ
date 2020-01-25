package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstEmployee extends CommonEntity {
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
	private String deleteFlg = null;
	private String createModuleId = null;
	private String createUserId = null;
	private String createYmd = null;
	private String updateModuleId = null;
	private String updateUserId = null;
	private String updateYmd = null;
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
	public String getBirthYmd() {
		return birthYmd;
	}
	public void setBirthYmd(String birthYmd) {
		this.birthYmd = birthYmd;
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
	public String getAuthorized() {
		return authorized;
	}
	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}
	public String getRetireYmd() {
		return retireYmd;
	}
	public void setRetireYmd(String retireYmd) {
		this.retireYmd = retireYmd;
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
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public String getCreateModuleId() {
		return createModuleId;
	}
	public void setCreateModuleId(String createModuleId) {
		this.createModuleId = createModuleId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateYmd() {
		return createYmd;
	}
	public void setCreateYmd(String createYmd) {
		this.createYmd = createYmd;
	}
	public String getUpdateModuleId() {
		return updateModuleId;
	}
	public void setUpdateModuleId(String updateModuleId) {
		this.updateModuleId = updateModuleId;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateYmd() {
		return updateYmd;
	}
	public void setUpdateYmd(String updateYmd) {
		this.updateYmd = updateYmd;
	}
}
