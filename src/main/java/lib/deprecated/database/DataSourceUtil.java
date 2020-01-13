package lib.deprecated.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DataSourceUtil<T> {

	public void getDataSource() throws NamingException, SQLException {
	    InitialContext initCon = new InitialContext(); //(1)
	    DataSource ds = (DataSource)initCon.lookup("java:comp/env/jdbc/empdb"); //(2)
	    Connection con = ds.getConnection(); //(3)JNDIリソースへのコネクト
	    Statement stmt = con.createStatement(); //(4)
	    ResultSet result = stmt.executeQuery("select * from city;"); //(5)SQL文の実行
	}

	private Connection open() throws NamingException, SQLException {
	    InitialContext initCon = new InitialContext(); //(1)
	    DataSource ds = (DataSource)initCon.lookup("java:comp/env/jdbc/empdb"); //(2)
	    Connection con = ds.getConnection(); //(3)JNDIリソースへのコネクト
	    return con;
	}

	public T selectKey(T param) {
		return null;
	}

	public List<T> select(String sql,T param,Class resultType) {
		List<T> resultList = new ArrayList<>();
		try (Connection connection = open();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()
		) {
			while(resultSet.next()) {
				T result = (T)resultType.getDeclaredConstructor().newInstance();
				// TODO 検索結果を詰め替え
				resultList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public int create(T param) {
		return 0;
	}

	public int updateKey(T param) {
		return 0;
	}

	public int update(String sql,T param) {
		return 0;
	}

	public int delete(T param) {
		return 0;
	}
}
