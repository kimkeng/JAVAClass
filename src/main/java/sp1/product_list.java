package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


//Module
public class product_list { //3
	Connection con = null;
	
	public product_list() { //즉시실행
		try {
			this.con = dbconfig.info(); //con을 클로즈 하게되면 없어져버린다
			//System.out.println(this.conn);
		}
		catch(Exception e) {
			System.out.println("Database 접속오류!!");
		}
	}
	//5 DTo와 관계없는 데이터 갯수를 파악
	public int data_ea() {
		PreparedStatement ps = null; //interface
		ResultSet rs = null;
		int ea = 0;;
		try {
			String sql = "select count(*) as cnt from product"; //갯수파악하는 sql
			ps = this.con.prepareStatement(sql);
			rs = ps.executeQuery(); // javaspl interface
			rs.next();
			ea = rs.getInt("cnt");  //데이터베이스 필드명
			//System.out.println(ea);
			
			this.con.close(); //데이터베이스는 항상 끊어줘야한다 ((중요))
		}
		catch(Exception e) {
			System.out.println("Database 문법오류 발생");
		}
		
		return ea;
	}
	
	//4 DTO와 연결하여 Database에 있는 값을 2차원 배역로 만드는 작업
	public ArrayList<ArrayList<String>> listdata(){
		PreparedStatement ps = null; //interface
		ResultSet rs = null;		
		ArrayList<ArrayList<String>> pd_list = new ArrayList<ArrayList<String>>(); //8
		try {
			//6 새로운 db연결을 재실행 함
			this.con = dbconfig.info(); //위에서 con을 종료했기때문에 다시 db연결  
			System.out.println(this.con);
			String sql = "select * from product order by pidx desc";//위에서 부터 차례대로 나올 수 있게
			ps = this.con.prepareStatement(sql);
			rs =ps.executeQuery();
			rs.next();
			
			//DTO작업 : setter로 가지고옴
			dto_product dp = new dto_product(); //dto가지고 오기
			while(rs.next()) { //데이터를 1차배열로 set으로 가지고 온다 한 데이터를 하나씩 
				dp.setPidx(rs.getString("pidx"));  //getString("mno") :실제 데이터베이스 필드 명
				dp.setPcode(rs.getString("pcode"));
				dp.setPname(rs.getString("pname"));
				dp.setPmoney(rs.getString("pmoney"));
				//dp.setPimg(rs.getString("pimg")); 필요없는건 지우고
				//dp.setPsale(rs.getString("psale")); 필요없음
				dp.setPuse(rs.getString("puse")); 
				pd_list.add(dp.db_data()); //2차원배열로 만들어서 추가
			}
			this.con.close();
		}
		catch(Exception e) {
			System.out.println("SQL 문법오류 발생");
		}
		return pd_list;  //2차원배열로 보내자 
		
	}

}