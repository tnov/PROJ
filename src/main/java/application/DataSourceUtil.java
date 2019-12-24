package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceUtil {

	public void getDataSource() throws NamingException, SQLException {
	    InitialContext initCon = new InitialContext(); //(1)
	    DataSource ds = (DataSource)initCon.lookup("java:comp/env/MySQL_JDBC"); //(2)
	    Connection con = ds.getConnection(); //(3)JNDIリソースへのコネクト
	    Statement stmt = con.createStatement(); //(4)
	    ResultSet result = stmt.executeQuery("select * from city;"); //(5)SQL文の実行
	}
}
