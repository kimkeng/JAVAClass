package sp1;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dto_user {
	String mno,mid,mpass,memail,mtel,marea,minter,mage,mdate;
	
	//Database => 1차원 배열 => return 2차원 배열로 전달
	public ArrayList<String> db_data(){
		ArrayList<String> lists = new ArrayList<String>();
		lists.add(getMno());	//Auto_increment
		lists.add(getMid());	//id
		lists.add(getMemail());	//email
		lists.add(getMtel());	//tell
		lists.add(getMage());	//age
		lists.add(getMdate());
		
		return lists;
	}
}
