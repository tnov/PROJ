package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstAuthorizedUser extends CommonEntity {
	private String userId = null;
	private String userPassword = null;
	private String organizationId = null;
	private String authorizedGroupId = null;
	private String lastUpdateYmd = null;
	private String lastUpdatePass = null;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getAuthorizedGroupId() {
		return authorizedGroupId;
	}
	public void setAuthorizedGroupId(String authorizedGroupId) {
		this.authorizedGroupId = authorizedGroupId;
	}
	public String getLastUpdateYmd() {
		return lastUpdateYmd;
	}
	public void setLastUpdateYmd(String lastUpdateYmd) {
		this.lastUpdateYmd = lastUpdateYmd;
	}
	public String getLastUpdatePass() {
		return lastUpdatePass;
	}
	public void setLastUpdatePass(String lastUpdatePass) {
		this.lastUpdatePass = lastUpdatePass;
	}
}
