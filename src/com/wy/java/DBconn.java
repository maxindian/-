package com.wy.java;

import java.sql.*;
import java.util.*;

public class DBconn {
	private Connection conn=null;
	public Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/source_manage";
			String username="root";
			String password="123456";
			
			conn=DriverManager.getConnection(url,username,password);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return conn;
	}
}
