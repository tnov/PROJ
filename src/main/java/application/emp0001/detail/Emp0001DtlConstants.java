package application.emp0001.detail;

public class Emp0001DtlConstants {
	public static final String CONTENTS_PATH = "/WEB-INF/jsp/application/emp0001/emp0001dtl.jsp";

	public static final String EMPLOYEE_ID = "社員ＩＤ";
	public static final String EMPLOYEE_NAME = "社員氏名";
	public static final String SEX = "性別";
	public static final String BIRTH_YMD = "生年月日";
	public static final String DEPARTMENT_NAME = "所属部署";
	public static final String JOINED_YMD = "入社日";
	public static final String RETIRE_YMD = "退社日";
	public static final String ZIP_CODE = "郵便番号";
	public static final String ADDRESS = "住所";
	public static final String TEL = "電話番号";
	public static final String PASSWORD = "パスワード";
	public static final String PASSWORD_CHK = "パスワード確認用";

	public static final String MESSAGE_ERROR_EMPLOYEE_ID_NOT_INPUT = "社員ＩＤを入力してください。";
	public static final String MESSAGE_ERROR_EMPLOYEE_ID_NOT_HALF_ALPHANUMERIC = "社員ＩＤは半角英数字で入力してください。";
	public static final String MESSAGE_ERROR_EMPLOYEE_NAME_NOT_INPUT = "社員氏名を入力してください。";
	public static final String MESSAGE_ERROR_SEX_NOT_INPUT = "性別を入力してください。";
	public static final String MESSAGE_ERROR_BIRTH_YMD_NOT_NUMBER = "生年月日は半角数字で入力してください。";
	public static final String MESSAGE_ERROR_BIRTH_YMD_NOT_DATE = "生年月日が不正な日付です。";
	public static final String MESSAGE_ERROR_JOINED_YMD_NOT_NUMBER = "入社日は半角数字で入力してください。";
	public static final String MESSAGE_ERROR_JOINED_YMD_NOT_DATE = "入社日が不正な日付です。";
	public static final String MESSAGE_ERROR_RETIRE_YMD_NOT_NUMBER = "退社日は半角数字で入力してください。";
	public static final String MESSAGE_ERROR_RETIRE_YMD_NOT_DATE = "退社日が不正な日付です。";
	public static final String MESSAGE_ERROR_ZIP_CODE_NOT_NUMBER = "郵便番号は半角数字で入力してください。";
	public static final String MESSAGE_ERROR_TEL_NOT_NUMBER = "電話番号は半角数字で入力してください。";
	public static final String MESSAGE_ERROR_AUTHORIZED_NOT_INPUT = "パスワードを入力してください。";
	public static final String MESSAGE_ERROR_AUTHORIZED_CHK_NOT_INPUT = "パスワード確認用を入力してください。";
	public static final String MESSAGE_ERROR_AUTHORIZED_CHK_NOT_EQUAL = "パスワードとパスワード確認用が一致しません。";

	public static final String MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE = "社員情報の保存に失敗しました。";
	public static final String MESSAGE_INFO_MST_EMPLOYEE_SAVE = "社員情報の保存に成功しました。";

	public static final String MODE_CREATE = "0";
	public static final String MODE_UPDATE = "1";
	public static final String MODE_DELETE = "2";
}
