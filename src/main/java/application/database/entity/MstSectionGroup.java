package application.database.entity;

import lib.database.entity.CommonEntity;

public class MstSectionGroup extends CommonEntity {
	private String sectionGroupId = null;
	private String sectionGroupName = null;
	private String sectionGroupComment = null;
	public String getSectionGroupId() {
		return sectionGroupId;
	}
	public void setSectionGroupId(String sectionGroupId) {
		this.sectionGroupId = sectionGroupId;
	}
	public String getSectionGroupName() {
		return sectionGroupName;
	}
	public void setSectionGroupName(String sectionGroupName) {
		this.sectionGroupName = sectionGroupName;
	}
	public String getSectionGroupComment() {
		return sectionGroupComment;
	}
	public void setSectionGroupComment(String sectionGroupComment) {
		this.sectionGroupComment = sectionGroupComment;
	}
}
