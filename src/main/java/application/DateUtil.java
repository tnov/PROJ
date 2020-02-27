package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DateUtil {

	public static final String DATE_FORMAT_YYYYMMDDHHMMSSMILLS = "yyyyMMddHHmmssSSS";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSMILLS_SEPARATE = "yyyy/MM/dd HH:mm:ss.SSS";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS_SEPARATE = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_YYYYMMDD_SEPARATE = "yyyy/MM/dd";
	public static final String DATE_FORMAT_HHMMSSMILLS = "HHmmssSSS";
	public static final String DATE_FORMAT_HHMMSSMILLS_SEPARATE = "HH:mm:ss.SSS";
	public static final String DATE_FORMAT_HHMMSS = "HHmmss";
	public static final String DATE_FORMAT_HHMMSS_SEPARATE = "HH:mm:ss";

	public static Timestamp getCurrentTimestamp() {
		Timestamp ts = null;
		try {
			// JNDIからDBデータソース取得
		    InitialContext initialContext = new InitialContext();
		    DataSource dataSource = (DataSource)initialContext.lookup(CommonConstants.JNDI_JDBC_EMPDB);
		    // QUERY作成
		    StringBuilder query = new StringBuilder();
		    query.append("select current_timestamp");
		    // コネクションの取得
		    // SQL実行
			try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query.toString())
					) {
				int i = 1;
				// ログイン情報を検索
				if (!statement.execute()) {
					// データなし
				} else {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						ts = resultSet.getTimestamp(1);
					}
				}
			} catch (SQLException e) {
				throw e;
			}
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return ts;
	}

	public static String getCurrentTimestamp(String format) {
		return formatString(getCurrentTimestamp(), format);
	}

	public static String formatString(Timestamp ts, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(ts);
	}

	public static Timestamp toTimestamp(String ts, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return new Timestamp(df.parse(ts).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 期間を求める(ms)
	public static long getInterval(Timestamp start,Timestamp end) {
		return end.getTime() - start.getTime();
	}

	// 期間を求める(ms)
	public static long getInterval(String start,String end, String format) {
		return getInterval(toTimestamp(end, format),toTimestamp(start, format));
	}

	// 日付を進める
	public static Timestamp add(Timestamp ts, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ts.getTime());
		calendar.add(field, amount);
		return new Timestamp(calendar.getTimeInMillis());
	}
}
