package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class product_modify {
	Connection con = null;
	
	private void dbcon() {
		try {
			this.con = dbconfig.info();
		}catch(Exception e) {
			System.out.println("오류");
		}
	}
	protected ArrayList<String> view_ok(String idx){
		ArrayList<String> onelist = new ArrayList<String>();
		PreparedStatement ps = null;
		try {
		this.dbcon();	//DB 메소드를 실행
		String sql = "select * from product where pidx=?";
		ps = this.con.prepareStatement(sql);
		ps.setString(1, idx);
		ResultSet rs = ps.executeQuery();
		rs.next();
		//DTO 사용안함 : 데이터가 1개만 필요 하므로
		//String pidx,pcode,pname,pmoney,pimg,psale,puse
		onelist.add(rs.getString("pidx"));
		onelist.add(rs.getString("pcode"));
		onelist.add(rs.getString("pname"));
		onelist.add(rs.getString("pmoney"));
		onelist.add(rs.getString("pimg"));
		onelist.add(rs.getString("psale"));
		onelist.add(rs.getString("puse"));
		ps.close();
		this.con.close();
		}catch(Exception e) {
			
			System.out.println("SQL 문법 오류!!");
		}
		return onelist;
	}
}
