package application.emp0001.upload.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import application.CheckUtil;
import application.CommonUtil;
import application.MessageManager;
import application.emp0001.Emp0001Util;
import application.emp0001.detail.Emp0001DtlConstants;
import application.emp0001.detail.Emp0001DtlForm;
import application.emp0001.upload.Emp0001UplConstants;
import application.emp0001.upload.Emp0001UplForm;
import lib.common.Constants;

@MultipartConfig(maxFileSize=1048576)
public class Emp0001UplCsvUpload extends HttpServlet {

	/** メッセージマネージャ */
	MessageManager message = MessageManager.getInstance();

	enum col {EMPLOYEE_ID
		,EMPLOYEE_NAME
		,SEX
		,BIRTH_YMD
		,ZIP_CODE
		,ADDRESS
		,TEL
		,JOINED_YMD
		,RETIRE_YMD
		,DEPARTMENT_ID
		,AUTHORIZED
		,DELETE_FLG};

		public Emp0001Util util = new Emp0001Util();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// リクエストの取得
		Emp0001UplForm form = new Emp0001UplForm();

		// クラス名取得
		final String className = new Object(){}.getClass().getName();

		String buttontype = req.getParameter("buttontype");

		String userId = (String)req.getSession().getAttribute("userId");

		Part part = req.getPart("file");
		String fileName = part.getSubmittedFileName();
		if (CheckUtil.isEmpty(fileName)) {
			// ファイル未設定
			setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(Emp0001UplConstants.MESSAGE_ERROR_FILE_NOT_SELECT),form);
			// 元画面遷移
			CommonUtil.dispReturn(req, resp, Emp0001UplConstants.CONTENTS_PATH);
			return;
		}
		List<String> errors = new ArrayList<>();
		try (InputStream is = part.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br =  new BufferedReader(isr)) {
			boolean existsHeader = false;
			while (br.ready()) {
				String line = br.readLine();
				if (existsHeader) {
					existsHeader = false;
					continue;
				}
				String[] item = splitLineWithComma(line);
				if (item.length == 12) {

					String employeeId = item[col.EMPLOYEE_ID.ordinal()];
					String employeeName = item[col.EMPLOYEE_NAME.ordinal()];
					String sex = item[col.SEX.ordinal()];
					String birthYmd = item[col.BIRTH_YMD.ordinal()];
					String zipCode = item[col.ZIP_CODE.ordinal()];
					String address = item[col.ADDRESS.ordinal()];
					String tel = item[col.TEL.ordinal()];
					String joinedYmd = item[col.JOINED_YMD.ordinal()];
					String retireYmd = item[col.RETIRE_YMD.ordinal()];
					String departmentId = item[col.DEPARTMENT_ID.ordinal()];
					String authorized = item[col.AUTHORIZED.ordinal()];
					String deleteFlg = item[col.DELETE_FLG.ordinal()];
					Emp0001DtlForm dtlForm = new Emp0001DtlForm();
					dtlForm.setEmployeeId(employeeId);
					dtlForm.setEmployeeName(employeeName);
					dtlForm.setSex(sex);
					dtlForm.setBirthYmd(birthYmd);
					dtlForm.setZipCode(zipCode);
					dtlForm.setAddress(address);
					dtlForm.setTel(tel);
					dtlForm.setJoinedYmd(joinedYmd);
					dtlForm.setRetireYmd(retireYmd);
					dtlForm.setDepartmentId(departmentId);
					dtlForm.setAuthorized(authorized);
					dtlForm.setDeleteFlg(deleteFlg);
					dtlForm.setCreate_module_id(className);
					dtlForm.setCreate_user_id(userId);
					dtlForm.setUpdate_module_id(className);
					dtlForm.setUpdate_user_id(userId);
					dtlForm.setMode(buttontype);

					// チェック処理
					List<String> messages = util.inputCheck(dtlForm);
					if (CheckUtil.isNotEmpty(messages)) {
						errors.addAll(messages);
					}

					// 保存処理
					if (util.save(dtlForm)) {
						// 成功時
						setMessage(req, resp, Constants.MESSAGE_TYPE_INFO, message.getMessage(Emp0001DtlConstants.MESSAGE_INFO_MST_EMPLOYEE_SAVE),form);
					}else {
						// 保存処理エラー
						setMessage(req, resp, Constants.MESSAGE_TYPE_ERROR, message.getMessage(Emp0001DtlConstants.MESSAGE_ERROR_MST_EMPLOYEE_NOT_SAVE),form);
					}
				}
			}
		}
		// 元画面遷移
		CommonUtil.dispReturn(req, resp, Emp0001UplConstants.CONTENTS_PATH);
	}


	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, List<String> messages, Emp0001UplForm form) throws ServletException, IOException {
		StringJoiner sj = new StringJoiner("\r\n");
		for (String message : messages) {
			sj.add(message);
		}
		setMessage(req,resp,type,sj.toString(),form);
	}

	private void setMessage(HttpServletRequest req, HttpServletResponse resp, String type, String message, Emp0001UplForm form) throws ServletException, IOException {
		// 画面項目セット
		req.setAttribute("form", form);
		// メッセージの設定
		List<String> messages = (List<String>)req.getAttribute(type);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(message);
		req.setAttribute(type, messages);
	}

    /**
     * カンマ区切りで行を分割し、文字列配列を返す。
     *
     * ※下記について、アンエスケープ後の文字列を返す。
     * 1. 値にカンマ(,)を含む場合は,値の前後をダブルクオート(")で囲う
     * 2. ダブルクオート(")は，2つのダブルクオートに置換する("")
     *
     * */
    private static String[] splitLineWithComma(String line) {
        // 分割後の文字列配列
        String[] arr = null;
        // 後ろに偶数個の「"」が現れる「,」にマッチする正規表現
        String REGEX_CSV_COMMA = ",(?=(([^\"]*\"){2})*[^\"]*$)";
        // 最初と最後の「"」にマッチする正規表現
        String REGEX_SURROUND_DOUBLEQUATATION = "^\"|\"$";
        // 「""」にマッチする正規表現*
        String REGEX_DOUBLEQUOATATION = "\"\"";

        try {
            // １、「"」で囲まれていない「,」で行を分割する。
            Pattern cPattern = Pattern.compile(REGEX_CSV_COMMA);
            String[] cols = cPattern.split(line, -1);

            arr = new String[cols.length];
            for (int i = 0, len = cols.length; i < len; i++) {
                String col = cols[i].trim();

                // ２、最初と最後に「"」があれば削除する。
                Pattern sdqPattern =
                    Pattern.compile(REGEX_SURROUND_DOUBLEQUATATION);
                Matcher matcher = sdqPattern.matcher(col);
                col = matcher.replaceAll("");

                // ３、エスケープされた「"」を戻す。
                Pattern dqPattern =
                    Pattern.compile(REGEX_DOUBLEQUOATATION);
                matcher = dqPattern.matcher(col);
                col = matcher.replaceAll("\"");

                arr[i] = col;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }
}
