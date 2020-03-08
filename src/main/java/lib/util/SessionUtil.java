package lib.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.StringJoiner;

public class SessionUtil {

	private static int TOKEN_LENGTH = 8 * 2;

	public static String getToken(String userId, String timestamp) {
		// トークン生成
		return createToken(userId,timestamp);
	}

	private static String createToken(String userId, String timestamp) {
		byte[] token = new byte[TOKEN_LENGTH];
		// userId timestampを複合可能な状態で文字列化
		StringJoiner sj = new StringJoiner("&=&");
		sj.add(userId);
		try {
			SecureRandom random =SecureRandom.getInstanceStrong();
			random.nextBytes(token);
			StringBuilder sb = new StringBuilder();
			for (byte ch : token) {
				sb.append(String.format("%02x", ch));
			}
			sj.add(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		sj.add(timestamp);
		return sj.toString();
	}
}
