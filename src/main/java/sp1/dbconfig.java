package sp1;

import java.sql.Connection;
import java.sql.DriverManager;

//database 환경 설정
public class dbconfig {
	public static Connection info() throws Exception{
		String db_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/web";
		String db_user = "root";
		String db_pass = "qa5425";
		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url,db_user,db_pass);
		return con;
	}
	
}
