package application.emp0002.detail;

import java.util.HashMap;
import java.util.Map;

import application.CommonForm;

public class Emp0002DtlForm extends CommonForm {

	private String backMode = null;

	private String mode = null;

	private String paramCustomerId = null;
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
	private String createModuleId = null;
	private String createUserId = null;
	private String updateModuleId = null;
	private String updateUserId = null;

	private Map<String, String> agreeMap = new HashMap<String, String>();


	public String getParamCustomerId() {
		return paramCustomerId;
	}
	public void setParamCustomerId(String paramCustomerId) {
		this.paramCustomerId = paramCustomerId;
	}
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
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getBackMode() {
		return backMode;
	}
	public void setBackMode(String backMode) {
		this.backMode = backMode;
	}
	public Map<String, String> getAgreeMap() {
		return agreeMap;
	}
	public void setAgreeMap(Map<String, String> agreeMap) {
		this.agreeMap = agreeMap;
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

}
