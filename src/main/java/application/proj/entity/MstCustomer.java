package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstCustomer extends CommonEntity {

	private String customerId = null;
	private String customerName = null;
	private String zipCode = null;
	private String address = null;
	private String liaison = null;
	private String staff = null;
	private String tel = null;
	private String developmentFlg = null;
	private String maintenanceFlg = null;
	private String operationFlg = null;
	private String infrastructureFlg = null;
	private String agreeYmd = null;
	private String agreeStatus = null;
	private String deleteFlg = null;


	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getLiaison() {
		return liaison;
	}
	public void setLiaison(String liaison) {
		this.liaison = liaison;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDevelopmentFlg() {
		return developmentFlg;
	}
	public void setDevelopmentFlg(String developmentFlg) {
		this.developmentFlg = developmentFlg;
	}
	public String getMaintenanceFlg() {
		return maintenanceFlg;
	}
	public void setMaintenanceFlg(String maintenanceFlg) {
		this.maintenanceFlg = maintenanceFlg;
	}
	public String getOperationFlg() {
		return operationFlg;
	}
	public void setOperationFlg(String operationFlg) {
		this.operationFlg = operationFlg;
	}
	public String getInfrastructureFlg() {
		return infrastructureFlg;
	}
	public void setInfrastructureFlg(String infrastructureFlg) {
		this.infrastructureFlg = infrastructureFlg;
	}
	public String getAgreeYmd() {
		return agreeYmd;
	}
	public void setAgreeYmd(String agreeYmd) {
		this.agreeYmd = agreeYmd;
	}
	public String getAgreeStatus() {
		return agreeStatus;
	}
	public void setAgreeStatus(String agreeStatus) {
		this.agreeStatus = agreeStatus;
	}
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}


}
