package lib.common.password;

public class PasswordConstants {
	public static final String INIT_PATH = "/password";
	public static final String ACTION = "/password";
	public static final String CONTENTS_PATH = "/WEB-INF/jsp/lib/password.jsp";
	public static final String DISPATCH_PATH = "/menu";

	public static final String MESSAGE_ERROR_AUTHORIZED_FAILD = "ユーザＩＤまたは、パスワードが正しくありません。";
	public static final String MESSAGE_ERROR_USER_ID_NOT_INPUT = "ユーザＩＤを入力してください。";
	public static final String MESSAGE_ERROR_PASSWORD_NOT_INPUT = "パスワードを入力してください。";
	public static final String MESSAGE_ERROR_NEW_PASSWORD_INPUT_HALF_CHARACTERS = "新パスワードは半角文字を入力してください。";
	public static final String MESSAGE_ERROR_NEW_PASSWORD_INPUT_SHORT_CHARACTERS = "新パスワードは8桁以上で入力してください。";
	public static final String MESSAGE_ERROR_NEW_PASSWORD_INPUT_LARGE_CHARACTERS = "新パスワードは20桁以内で入力してください。";
	public static final String MESSAGE_ERROR_CONFIRM_PASSWORD_INPUT_HALF_CHARACTERS = "新パスワード確認用は半角文字を入力してください。";
	public static final String MESSAGE_ERROR_CONRIRM_PASSWORD_INPUT_SHORT_CHARACTERS = "新パスワード確認用は8桁以上で入力してください。";
	public static final String MESSAGE_ERROR_CONRIRM_PASSWORD_INPUT_LARGE_CHARACTERS = "新パスワード確認用は20桁以内で入力してください。";
	public static final String MESSAGE_ERROR_NEW_PASSWORD_NO_MATCH = "新パスワードと新パスワード確認用が一致しません。";
	public static final String MESSAGE_ERROR_NEW_PASSWORD_DUPLICATE = "新パスワードが前回パスワードと一致しています。";
}
