package application.emp0002;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import application.CheckUtil;
import application.emp0002.detail.Emp0002DtlConstants;
import application.emp0002.detail.Emp0002DtlForm;
import application.proj.dao.MstCustomerDao;
import application.proj.entity.MstCustomer;

public class Emp0002Util {
	public boolean existsEmployeeId(String employeeId) {
		return false;
	}
	// 入力チェック処理
	public List<String> inputCheck(Emp0002DtlForm form) {
		List<String> messages = new ArrayList<>();

		// 顧客ID 必須チェック
		if (CheckUtil.isEmpty(form.getCustomerId())) {
			messages.add(Emp0002DtlConstants.MESSAGE_ERROR_CUSTOMER_ID_NOT_INPUT);
		// 顧客ID 半角英数字チェック
		}else if(!Pattern.matches(Emp0002Constants.REGEX_HALF_ALPHANUMERIC, form.getCustomerId())) {
			messages.add(Emp0002DtlConstants.MESSAGE_ERROR_CUSTOMER_ID_NOT_HALF_ALPHANUMERIC);
		}

		// 顧客名称 必須チェック
		if (CheckUtil.isEmpty(form.getCustomerName())) {
			messages.add(Emp0002DtlConstants.MESSAGE_ERROR_CUSTOMER_NAME_NOT_INPUT);
		}

		// 郵便番号 数値チェック
		if(!CheckUtil.isEmpty(form.getZipCode())
			&& !Pattern.matches(Emp0002Constants.REGEX_NUMBER, form.getZipCode())) {
			messages.add(Emp0002DtlConstants.MESSAGE_ERROR_ZIP_CODE_NOT_NUMBER);
		}

		// 電話番号 数値チェック
		if(!CheckUtil.isEmpty(form.getTel())
			&& !Pattern.matches(Emp0002Constants.REGEX_NUMBER, form.getTel())) {
			messages.add(Emp0002DtlConstants.MESSAGE_ERROR_TEL_NOT_NUMBER);
		}

		return messages;
	}


	/**
	 * 顧客情報CSV保存
	 *
	 * @param form
	 * @return 処理結果
	 */
	public boolean save(Emp0002DtlForm form) {
		boolean result = false;
		MstCustomer data = null;
		MstCustomerDao dao = new MstCustomerDao();
		// 登録処理
		if (Emp0002DtlConstants.MODE_CREATE.equals(form.getMode())) {
			data = new MstCustomer();
			data.setCustomerId(form.getCustomerId());
			data.setCustomerName(form.getCustomerName());
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setLiaison(form.getLiaison());
			data.setStaff(form.getStaff());
			data.setTel(form.getTel());
			data.setDevelopmentFlg(form.getDevelopmentFlg());
			data.setMaintenanceFlg(form.getMaintenanceFlg());
			data.setOperationFlg(form.getOperationFlg());
			data.setInfrastructureFlg(form.getInfrastructureFlg());
			data.setAgreeYmd(form.getAgreeYmd());
			data.setAgreeStatus(form.getAgreeStatus());
			data.setDeleteFlg(form.getDeleteFlg());
			data.setCreateModuleId(form.getCreateModuleId());
			data.setCreateUserId(form.getCreateUserId());
			data.setUpdateModuleId(form.getUpdateModuleId());
			data.setUpdateUserId(form.getUpdateUserId());
			result = dao.insert(data);

			// 更新処理
		} else if (Emp0002DtlConstants.MODE_UPDATE.equals(form.getMode())) {

			String employeeId = form.getCustomerId();
			MstCustomer key = new MstCustomer();
			key.setCustomerId(employeeId);
			data = dao.getMstCustomer(key);
			if (data == null) {
				return false;
			}
			data.setCustomerId(form.getCustomerId());
			data.setCustomerName(form.getCustomerName());
			data.setZipCode(form.getZipCode());
			data.setAddress(form.getAddress());
			data.setLiaison(form.getLiaison());
			data.setStaff(form.getStaff());
			data.setTel(form.getTel());
			data.setDevelopmentFlg(form.getDevelopmentFlg());
			data.setMaintenanceFlg(form.getMaintenanceFlg());
			data.setOperationFlg(form.getOperationFlg());
			data.setInfrastructureFlg(form.getInfrastructureFlg());
			data.setAgreeYmd(form.getAgreeYmd());
			data.setAgreeStatus(form.getAgreeStatus());
			data.setDeleteFlg(form.getDeleteFlg());
			data.setCreateModuleId(form.getCreateModuleId());
			data.setCreateUserId(form.getCreateUserId());
			data.setUpdateModuleId(form.getUpdateModuleId());
			data.setUpdateUserId(form.getUpdateUserId());

			result = dao.update(data);

			// 削除処理
		} else if (Emp0002DtlConstants.MODE_DELETE.equals(form.getMode())) {

			String employeeId = form.getCustomerId();
			MstCustomer key = new MstCustomer();
			key.setCustomerId(employeeId);
			data = dao.getMstCustomer(key);
			if (data == null) {
				return false;
			}
			data.setCustomerId(form.getCustomerId());

			result = dao.delete(data);
		}
		return result;
	}
}
