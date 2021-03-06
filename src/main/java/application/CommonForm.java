package application;

public class CommonForm {

	private String loginUserId = null;

	private String moduleId = null;

	private String token = null;

	private String movePath = null;

	// 1ページ内の行数
	private String lineSize = null;

	// 現在のページ数
	private String currentPage = null;

	// 全体ページ数
	private String pageSize = null;

	// 全体取得上限
	private String lineLimit = null;

	public String getMovePath() {
		return movePath;
	}

	public void setMovePath(String movePath) {
		this.movePath = movePath;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getLineSize() {
		return lineSize;
	}

	public void setLineSize(String lineSize) {
		this.lineSize = lineSize;
	}

	public String getLineLimit() {
		return lineLimit;
	}

	public void setLineLimit(String lineLimit) {
		this.lineLimit = lineLimit;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}
