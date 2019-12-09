package application.database.entity;

public class MstAuthorized extends CommonEntity {
	private String authorizedId = null;
	private String authorizedName = null;
	private String authorizedComment = null;
	private String authorizedLevel = null;
	private String organizetionId = null;
	public String getAuthorizedId() {
		return authorizedId;
	}
	public void setAuthorizedId(String authorizedId) {
		this.authorizedId = authorizedId;
	}
	public String getAuthorizedName() {
		return authorizedName;
	}
	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}
	public String getAuthorizedComment() {
		return authorizedComment;
	}
	public void setAuthorizedComment(String authorizedComment) {
		this.authorizedComment = authorizedComment;
	}
	public String getAuthorizedLevel() {
		return authorizedLevel;
	}
	public void setAuthorizedLevel(String authorizedLevel) {
		this.authorizedLevel = authorizedLevel;
	}
	public String getOrganizetionId() {
		return organizetionId;
	}
	public void setOrganizetionId(String organizetionId) {
		this.organizetionId = organizetionId;
	}
}
