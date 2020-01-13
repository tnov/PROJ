package application.proj.entity;

import lib.deprecated.database.entity.CommonEntity;

public class MstFunction extends CommonEntity {
	private String functionId = null;
	private String functionName = null;
	private String functionPath = null;
	private String functionComment = null;
	private String authorizedId = null;
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionPath() {
		return functionPath;
	}
	public void setFunctionPath(String functionPath) {
		this.functionPath = functionPath;
	}
	public String getFunctionComment() {
		return functionComment;
	}
	public void setFunctionComment(String functionComment) {
		this.functionComment = functionComment;
	}
	public String getAuthorizedId() {
		return authorizedId;
	}
	public void setAuthorizedId(String authorizedId) {
		this.authorizedId = authorizedId;
	}
}
