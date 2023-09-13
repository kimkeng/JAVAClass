package sp1;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

//1. DTO 만들기 2. Modeul 1차원 3. dto 2차원넣기
//DTO
@Getter
@Setter
public class dto_product {
	String pidx,pcode,pname,pmoney,pimg,psale,puse;//2

	//1차원 배열의 생성
	public ArrayList<String> db_data(){
		ArrayList<String> lists = new ArrayList<String>();
		//7 getter로 가지고 오기 앞의 list setter의 순서대로 가지고온다 getter와 setter의 개수는 같아야 한다
		lists.add(getPidx()); //auto 0
		lists.add(getPcode()); //아이디 1
		lists.add(getPname()); //이메일 2
		lists.add(getPmoney()); //연락처 3
		lists.add(getPuse()); //가입일자 4
		//System.out.println(lists);
		return lists;
	}
}
