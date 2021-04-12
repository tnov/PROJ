package application.database.entity;

import lib.database.entity.CommonEntity;

public class MstSection extends CommonEntity {
	private String sectionGroupId = null;
	private String sectionId = null;
	private String sectionName = null;
	private String sectionComment = null;
	public String getSectionGroupId() {
		return sectionGroupId;
	}
	public void setSectionGroupId(String sectionGroupId) {
		this.sectionGroupId = sectionGroupId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionComment() {
		return sectionComment;
	}
	public void setSectionComment(String sectionComment) {
		this.sectionComment = sectionComment;
	}
}
