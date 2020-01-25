package application;

public class CommonForm {

	private String movePath = null;

	// 1ページ内の行数
	private String lineSize = null;

	// 現在のページ数
	private String currentPage = null;

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
}
