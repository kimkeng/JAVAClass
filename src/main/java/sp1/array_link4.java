package sp1;

import java.util.ArrayList;
import java.util.List;

//DAO, DTO 형태의 구성
public class array_link4 {
	//DAO파트
	public static void main(String[] args) {
		new box().datacall();
	}
}
class box{
	
	dto dt = new dto(); //setter, getter
	
	public void datacall() {
		//return 받는 method 형태가 2차원 배열이므로 선언하여 사용
		List<ArrayList<String>> select = this.list();
		System.out.println(select);
	}
	
	//Database연결 및 데이터를 배열화 하는 메소드 필요.
	public List<ArrayList<String>> list(){
		List<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
		String data[][] = new String [][] {
			{"hong","hong@nate.com"},
			{"lee","leeesu@gmail.com"}
		};
		
		//반복문을 이용해서 setter에 값을 이관함
		int w = 0;
		while(w<data.length) {
			this.dt.setUserid(data[w][0]);
			this.dt.setEmail(data[w][1]);
			//dto에 1차원 배열 메소드를 호출하여 return 값을 받아서 2차원 배열로 삽입
			al.add(this.dt.database());
			w++;
		}
		return al;	//2차원 배열 반환
	}
}