package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstOrganization extends CommonEntity {
	private String organizationId = null;
	private String organizationName = null;
	private String organizationComment = null;
	private String organizationParentId = null;
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationComment() {
		return organizationComment;
	}
	public void setOrganizationComment(String organizationComment) {
		this.organizationComment = organizationComment;
	}
	public String getOrganizationParentId() {
		return organizationParentId;
	}
	public void setOrganizationParentId(String organizationParentId) {
		this.organizationParentId = organizationParentId;
	}
}
