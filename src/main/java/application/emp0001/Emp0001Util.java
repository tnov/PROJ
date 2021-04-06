package application.emp0001;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import application.CheckUtil;
import application.CommonConstants;
import application.MessageManager;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import application.proj.dao.MstEmployeeDao;
import application.proj.entity.MstEmployee;

public class Emp0001Util {

	/** メッセージマネージャ */
	MessageManager message = MessageManager.getInstance();

	public boolean existsEmployeeId(String employeeId) {
		return false;
	}
	// 入力チェック処理
	public List<String> inputCheck(Emp0001DtlForm form) {
		List<String> messages = new ArrayList<>();

		// 社員ID 必須チェック
		if (CheckUtil.isEmpty(form.getEmployeeId())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_ID_NOT_INPUT));
		// 社員ID 半角英数字チェック
		}else if(!Pattern.matches(Emp0001Constants.REGEX_HALF_ALPHANUMERIC, form.getEmployeeId())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_ID_NOT_HALF_ALPHANUMERIC));
		}

		// 社員氏名 必須チェック
		if (CheckUtil.isEmpty(form.getEmployeeName())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_NAME_NOT_INPUT));
		}

		if(!CheckUtil.isEmpty(form.getBirthYmd())) {
			// 生年月日 数値チェック
			if(!Pattern.matches(Emp0001Constants.REGEX_NUMBER, form.getBirthYmd())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_BIRTH_YMD_NOT_NUMBER));
			// 生年月日 日付妥当性チェック
			}else if(!CheckUtil.dateValidChk(form.getBirthYmd(), CommonConstants.FORMAT_YYYYMMDD)){
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_BIRTH_YMD_NOT_DATE));
			}
		}

		if(!CheckUtil.isEmpty(form.getJoinedYmd())) {
			// 入社日 数値チェック
			if(!Pattern.matches(Emp0001Constants.REGEX_NUMBER, form.getJoinedYmd())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_JOINED_YMD_NOT_NUMBER));
			// 入社日 日付妥当性チェック
			}else if(!CheckUtil.dateValidChk(form.getJoinedYmd(), CommonConstants.FORMAT_YYYYMMDD)){
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_JOINED_YMD_NOT_DATE));
			}
		}

		if(!CheckUtil.isEmpty(form.getRetireYmd())) {
			// 退社日 数値チェック
			if(!Pattern.matches(Emp0001Constants.REGEX_NUMBER, form.getRetireYmd())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_RETIRE_YMD_NOT_NUMBER));
			// 退社日 日付妥当性チェック
			}else if(!CheckUtil.dateValidChk(form.getRetireYmd(), CommonConstants.FORMAT_YYYYMMDD)){
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_RETIRE_YMD_NOT_DATE));
			}
		}

		if(!CheckUtil.isEmpty(form.getZipCode())){
			// 郵便番号 数値チェック
			if(!Pattern.matches(Emp0001Constants.REGEX_NUMBER, form.getZipCode())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_ZIP_CODE_NOT_NUMBER));
			}
		}

		if(!CheckUtil.isEmpty(form.getTel())) {
			// 電話番号 数値チェック
			if(!Pattern.matches(Emp0001Constants.REGEX_NUMBER, form.getTel())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_TEL_NOT_NUMBER));
			}
		}

		// パスワード 必須チェック
		if (CheckUtil.isEmpty(form.getAuthorized())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_AUTHORIZED_NOT_INPUT));
		// パスワード確認用 必須チェック
		}else if (CheckUtil.isEmpty(form.getAuthorizedChk())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_AUTHORIZED_CHK_NOT_INPUT));
		// パスワード、パスワード確認用一致チェック
		}else if (!form.getAuthorized().equals(form.getAuthorizedChk())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_AUTHORIZED_CHK_NOT_EQUAL));
		}


		return messages;
	}


	/**
	 * 社員情報CSV保存
	 *
	 * @param form
	 * @return 処理結果
	 */
	public boolean save(Emp0001DtlForm form) {
		boolean result = false;
		MstEmployee data = null;
		MstEmployeeDao dao = new MstEmployeeDao();
		// 登録処理
		if (Emp0001DtlConstants.MODE_CREATE.equals(form.getMode())) {
			data = new MstEmployee();
			data.setEmployeeId(form.getEmployeeId());
			data.setEmployeeName(form.getEmployeeName());
			data.setSex(form.getSex());
			data.setBirthYmd(form.getBirthYmd());
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setTel(form.getTel());
			data.setJoinedYmd(form.getJoinedYmd());
			data.setRetireYmd(form.getRetireYmd());
			data.setDepartmentId(form.getDepartmentId());
//			data.setAuthorized(form.getAuthorized());
			data.setAuthorized("0");
			data.setDeleteFlg(form.getDeleteFlg());
			data.setCreateModuleId(form.getCreate_module_id());
			data.setCreateUserId(form.getCreate_user_id());
			data.setUpdateModuleId(form.getUpdate_module_id());
			data.setUpdateUserId(form.getUpdate_user_id());
			result = dao.insert(data);

			// 更新処理
		} else if (Emp0001DtlConstants.MODE_UPDATE.equals(form.getMode())) {

			String employeeId = form.getEmployeeId();
			MstEmployee key = new MstEmployee();
			key.setEmployeeId(employeeId);
			data = dao.getMstEmployee(key);
			if (data == null) {
				return false;
			}
			data.setEmployeeId(form.getEmployeeId());
			data.setEmployeeName(form.getEmployeeName());
			data.setSex(form.getSex());
			data.setBirthYmd(form.getBirthYmd());
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setTel(form.getTel());
			data.setJoinedYmd(form.getJoinedYmd());
			data.setRetireYmd(form.getRetireYmd());
			data.setDepartmentId(form.getDepartmentId());
			data.setAuthorized(form.getAuthorized());
			data.setDeleteFlg(form.getDeleteFlg());
			data.setUpdateModuleId(form.getUpdate_module_id());
			data.setUpdateUserId(form.getUpdate_user_id());

			result = dao.update(data, key);

			// 削除処理
		} else if (Emp0001DtlConstants.MODE_DELETE.equals(form.getMode())) {

			String employeeId = form.getEmployeeId();
			MstEmployee key = new MstEmployee();
			key.setEmployeeId(employeeId);
			data = dao.getMstEmployee(key);
			if (data == null) {
				return false;
			}
			data.setEmployeeId(form.getEmployeeId());

			result = dao.delete(data);
		}
		return result;
	}
}
