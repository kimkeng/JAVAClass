package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
//12
public class product_delete {
	Connection con = null;
	public product_delete() {
		try {
			this.con = dbconfig.info(); //dconfig 안의 info를 가지고오자
		}
		catch(Exception e) {
			System.out.println("DB 접속오류!!");
		}
	}
	//삭제 Module
	protected int delete_ok(String idx) throws Exception{
		//13
		String sql ="delete from product where pidx=?";
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, idx);
		int oksign = ps.executeUpdate(); //0은 실패 1은 성공
		
		ps.close();
		this.con.close();
		return oksign;
	}
}
