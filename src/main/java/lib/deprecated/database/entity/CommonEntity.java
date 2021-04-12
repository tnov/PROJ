package lib.deprecated.database.entity;

import java.sql.Timestamp;

abstract public class CommonEntity {
	private String deleteFlg = null;
	private String createModuleId = null;
	private String createUserId = null;
	private Timestamp createYmd = null;
	private String updateModuleId = null;
	private String updateUserId = null;
	private Timestamp updateYmd = null;

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
	public Timestamp getCreateYmd() {
		return createYmd;
	}
	public void setCreateYmd(Timestamp createYmd) {
		this.createYmd = createYmd;
	}
	public Timestamp getUpdateYmd() {
		return updateYmd;
	}
	public void setUpdateYmd(Timestamp updateYmd) {
		this.updateYmd = updateYmd;
	}
}
