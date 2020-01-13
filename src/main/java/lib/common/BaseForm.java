package lib.common;

import java.util.List;

public abstract class BaseForm {
	private List<String> errorMessages;
	private List<String> warningMessages;
	private List<String> infoMessages;
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public List<String> getWarningMessages() {
		return warningMessages;
	}
	public void setWarningMessages(List<String> warningMessages) {
		this.warningMessages = warningMessages;
	}
	public List<String> getInfoMessages() {
		return infoMessages;
	}
	public void setInfoMessages(List<String> infoMessages) {
		this.infoMessages = infoMessages;
	}
}
