package application.emp0002.list;

import java.util.HashMap;
import java.util.List;

import application.CommonForm;
import application.emp0002.Emp0002DataBean;

public class Emp0002LstForm extends CommonForm {

	private String customerId = null;
	private String customerName = null;
	private String[] devType = null;
	public String[] getDevType() {
		return devType;
	}
	public void setDevType(String[] devType) {
		this.devType = devType;
	}
	private String development = null;
	private String maintenance = null;
	private String operation = null;
	private String infrastructure = null;
	private String agreeYmdFrom = null;
	private String agreeYmdTo = null;
	private String[] agreeStatus = null;

	public String[] getAgreeStatus() {
		return agreeStatus;
	}
	public void setAgreeStatus(String[] agreeStatus) {
		this.agreeStatus = agreeStatus;
	}
	private HashMap<String, String> departmentMap = new HashMap<String, String>();

	private List<Emp0002DataBean> resultList = null;

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
	public String getDevelopment() {
		return development;
	}
	public void setDevelopment(String development) {
		this.development = development;
	}
	public String getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getInfrastructure() {
		return infrastructure;
	}
	public void setInfrastructure(String infrastructure) {
		this.infrastructure = infrastructure;
	}
	public String getAgreeYmdFrom() {
		return agreeYmdFrom;
	}
	public void setAgreeYmdFrom(String agreeYmdFrom) {
		this.agreeYmdFrom = agreeYmdFrom;
	}
	public String getAgreeYmdTo() {
		return agreeYmdTo;
	}
	public void setAgreeYmdTo(String agreeYmdTo) {
		this.agreeYmdTo = agreeYmdTo;
	}
	public List<Emp0002DataBean> getResultList() {
		return resultList;
	}
	public void setResultList(List<Emp0002DataBean> resultList) {
		this.resultList = resultList;
	}
	public HashMap<String, String> getDepartmentMap() {
		return departmentMap;
	}
	public void setDepartmentMap(HashMap<String, String> departmentMap) {
		this.departmentMap = departmentMap;
	}
}
