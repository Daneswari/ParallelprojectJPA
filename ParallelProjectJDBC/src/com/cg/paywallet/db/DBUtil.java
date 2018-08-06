package com.cg.paywallet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.cg.paywallet.exception.PaywalletException;

public class DBUtil {
	public static Connection getConnection() throws PaywalletException{
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url,"system","root");
		}catch(ClassNotFoundException e){
		throw new PaywalletException(e.getMessage());
		}catch(SQLException e1){
		throw new PaywalletException(e1.getMessage());
		}
}
}
