package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstAuthorizedUser extends CommonEntity {
	private String userId = null;
	private String userPassword = null;
	private String lastUpdateYmd = null;
	private String lastUpdatePassword = null;
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
	public String getLastUpdateYmd() {
		return lastUpdateYmd;
	}
	public void setLastUpdateYmd(String lastUpdateYmd) {
		this.lastUpdateYmd = lastUpdateYmd;
	}
	public String getLastUpdatePassword() {
		return lastUpdatePassword;
	}
	public void setLastUpdatePassword(String lastUpdatePassword) {
		this.lastUpdatePassword = lastUpdatePassword;
	}
}
