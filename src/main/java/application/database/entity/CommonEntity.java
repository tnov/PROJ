package application.database.entity;

abstract class CommonEntity {
	private String deleteFlg = null;
	private String createModuleId = null;
	private String createUserId = null;
	private String createYmd = null;
	private String updateModuleId = null;
	private String updateUserId = null;
	private String updateYmd = null;

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
