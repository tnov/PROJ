package application;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * ResourceBundleを使用したメッセージ
 */
public class MessageManager {

	static MessageManager messageManager = null;
	ResourceBundle resourceBundle = null;
	Map<String,ResourceBundle> resourceBundleMap = null;

	private MessageManager() {
	}

	/**
	 * MessageManagerインスタンス取得
	 * @return MessageManagerインスタンス
	 */
	public static MessageManager getInstance() {
		return getInstance(CommonConstants.MESSAGE_PROPERTIES);
	}

	/**
	 * MessageManagerインスタンス取得
	 * @param baseName クラスパス配下のメッセージファイル
	 * @return MessageManagerインスタンス
	 */
	public static MessageManager getInstance(String baseName) {
		if (messageManager == null) {
			messageManager = new MessageManager();
			messageManager.resourceBundleMap = new HashMap<>();
			messageManager.resourceBundle = ResourceBundle.getBundle(baseName,Locale.getDefault());
			messageManager.resourceBundleMap.put(baseName, messageManager.resourceBundle);
		}
		if (messageManager.resourceBundleMap.containsKey(baseName)) {
			messageManager.resourceBundle = messageManager.resourceBundleMap.get(baseName);
		} else {
			messageManager.resourceBundle = ResourceBundle.getBundle(baseName,Locale.getDefault());
			messageManager.resourceBundleMap.put(baseName, messageManager.resourceBundle);
		}
		return messageManager;
	}

	/**
	 * メッセージ取得処理
	 * @param key 取得キー
	 * @param params 置換文字列 {0},{1}...
	 * @return メッセージ
	 */
	public String getMessage(String key, String... params) {
		String message = resourceBundle.getString(key);
		message = MessageFormat.format(message, (Object)params);
		return message;
	}

	/**
	 * クリア
	 */
	public void clear() {
		messageManager.resourceBundleMap.clear();
		messageManager.resourceBundle = null;
	}
}
