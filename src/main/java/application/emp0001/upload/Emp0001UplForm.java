package application.emp0001.upload;

import java.io.File;

import application.CommonForm;

public class Emp0001UplForm extends CommonForm {

	private String fileName = null;

	private File file = null;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
