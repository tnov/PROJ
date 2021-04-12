package application.emp0001;

import java.util.ArrayList;
import java.util.List;

import application.CheckUtil;
import application.CommonConstants;
import application.DateUtil;
import application.database.dao.MstAuthorizedUserDao;
import application.database.dao.MstEmployeeDao;
import application.database.entity.MstAuthorizedUser;
import application.database.entity.MstEmployee;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import application.emp0001.list.Emp0001LstConstants;
import application.emp0001.list.Emp0001LstForm;
import lib.util.MessageManager;

public class Emp0001Util {

	/** メッセージマネージャ */
	MessageManager message = MessageManager.getInstance();

	public boolean existsEmployeeId(String employeeId) {
		return false;
	}

	// 入力チェック処理
	public List<String> searchCheck(Emp0001LstForm form) {
		List<String> messages = new ArrayList<>();

		// 入社日 日付妥当性チェック
		if(!CheckUtil.dateValidChk(form.getJoinedYmdFrom(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN)){
			messages.add(message.getMessage(Emp0001LstConstants.MESSAGE_ERROR_JOINED_YMD_FROM_NOT_DATE));
		}
		if(!CheckUtil.dateValidChk(form.getJoinedYmdTo(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN)){
			messages.add(message.getMessage(Emp0001LstConstants.MESSAGE_ERROR_JOINED_YMD_TO_NOT_DATE));
		}
		// 入社年月日の相関チェック
		if (CheckUtil.isNotEmpty(form.getJoinedYmdFrom()) && CheckUtil.isNotEmpty(form.getJoinedYmdTo())) {
			// 入社年月日from > 入社年月日to
			if (form.getJoinedYmdFrom().compareTo(form.getJoinedYmdTo()) > 0) {
				messages.add(message.getMessage(Emp0001LstConstants.MESSAGE_ERROR_JOINED_YMD_FROM_TO_ILLEEGAL_RELATIONAL));
			}
		}

		return messages;
	}

	// 入力チェック処理
	public List<String> inputCheck(Emp0001DtlForm form) {
		List<String> messages = new ArrayList<>();

		// 社員ID 必須チェック
		if (CheckUtil.isEmpty(form.getEmployeeId())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_ID_NOT_INPUT));
		// 社員ID 半角英数字チェック
		}else if(!CheckUtil.isHalfWidthString(form.getEmployeeId())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_ID_NOT_HALF_ALPHANUMERIC));
		}
		// 社員氏名 必須チェック
		if (CheckUtil.isEmpty(form.getEmployeeName())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_EMPLOYEE_NAME_NOT_INPUT));
		}
		// 生年月日 日付必須チェック
		if (CheckUtil.isEmpty(form.getBirthYmd())) {
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_BIRTH_YMD_NOT_INPUT));
		// 生年月日 日付妥当性チェック
		} else if(!CheckUtil.dateValidChk(form.getBirthYmd(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN)){
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_BIRTH_YMD_NOT_DATE));
		}
		// 入社日 日付妥当性チェック
		if(!CheckUtil.dateValidChk(form.getJoinedYmd(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN)){
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_JOINED_YMD_NOT_DATE));
		}
		// 退社日 日付妥当性チェック
		if(!CheckUtil.dateValidChk(form.getRetireYmd(), DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN)){
			messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_RETIRE_YMD_NOT_DATE));
		}
		if(!CheckUtil.isEmpty(form.getZipCode())){
			// 郵便番号 数値チェック
			if(!CheckUtil.isNumberString(form.getZipCode())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_ZIP_CODE_NOT_NUMBER));
			}
		}
		if(!CheckUtil.isEmpty(form.getTel())) {
			// 電話番号 数値チェック
			if(!CheckUtil.isNumberString(form.getTel())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_TEL_NOT_NUMBER));
			}
		}
		if (Emp0001DtlConstants.MODE_CREATE.equals(form.getMode())) {
			// パスワード 必須チェック
			if (CheckUtil.isEmpty(form.getPassword())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_PASSWORD_NOT_INPUT));
			// パスワード確認用 必須チェック
			}else if (CheckUtil.isEmpty(form.getPasswordChk())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_PASSWORD_CHK_NOT_INPUT));
			// パスワード、パスワード確認用一致チェック
			}else if (!form.getPassword().equals(form.getPasswordChk())) {
				messages.add(message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_PASSWORD_CHK_NOT_EQUAL));
			}
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
		MstAuthorizedUserDao authorizedUserDao = new MstAuthorizedUserDao();
		// 登録処理
		if (Emp0001DtlConstants.MODE_CREATE.equals(form.getMode())) {
			data = new MstEmployee();
			data.setEmployeeId(form.getEmployeeId());
			data.setEmployeeName(form.getEmployeeName());
			data.setSex(form.getSex());
			data.setBirthYmd(DateUtil.unformatDateString(form.getBirthYmd(),DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setTel(form.getTel());
			data.setJoinedYmd(DateUtil.unformatDateString(form.getJoinedYmd(),DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
			data.setRetireYmd(DateUtil.unformatDateString(form.getRetireYmd(),DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
			data.setDepartmentId(form.getDepartmentId());
			data.setDeleteFlg(CommonConstants.DELETE_FLG_OFF);
			data.setCreateModuleId(form.getModuleId());
			data.setCreateUserId(form.getLoginUserId());
			data.setUpdateModuleId(form.getModuleId());
			data.setUpdateUserId(form.getLoginUserId());
			result = dao.insert(data);
			if (result) {
				// 認証マスタ登録
				MstAuthorizedUser authorizedUser = new MstAuthorizedUser();
				authorizedUser.setUserId(form.getEmployeeId());
				authorizedUser.setUserPassword(form.getPassword());
				authorizedUser.setDeleteFlg(CommonConstants.DELETE_FLG_OFF);
				authorizedUser.setCreateModuleId(form.getModuleId());
				authorizedUser.setCreateUserId(form.getLoginUserId());
				authorizedUser.setUpdateModuleId(form.getModuleId());
				authorizedUser.setUpdateUserId(form.getLoginUserId());
				result = authorizedUserDao.insert(authorizedUser);
			}
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
			data.setBirthYmd(DateUtil.unformatDateString(form.getBirthYmd(),DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setTel(form.getTel());
			data.setJoinedYmd(DateUtil.unformatDateString(form.getJoinedYmd(),DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
			data.setRetireYmd(DateUtil.unformatDateString(form.getRetireYmd(),DateUtil.DATE_FORMAT_YYYYMMDD_HYPHEN));
			data.setDepartmentId(form.getDepartmentId());
			data.setDeleteFlg(CommonConstants.DELETE_FLG_OFF);
			data.setUpdateModuleId(form.getModuleId());
			data.setUpdateUserId(form.getLoginUserId());

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
