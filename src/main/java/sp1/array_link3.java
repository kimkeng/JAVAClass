package sp1;

import java.util.ArrayList;

//2차원 배열에 대한 응용편 ArrayList 필수
/*
 응용문제
 김경민, 배유미, 김승균, 이철의, 장진호, 홍사라, 박병준, 전정호, 이경선, 최현제, 서강인, 염무원
 
 [결과]
 [[A조,김경민,배유미,김승균],[B조,이철의,장진호,홍사라],[C조,박병준,전정호,이경선],[D조,최현제,서강인,염무원]]
 */
public class array_link3 {

	public static void main(String[] args) {
		//코드 및 결과 출력
		new ex().exe();
	}

}
class ex{
	public void exe() {
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("A조");
		a1.add("김경민");
		a1.add("배유미");
		a1.add("김승균");
		al.add(a1);
		System.out.println(al);
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("B조");
		a2.add("이철의");
		a2.add("장진호");
		a2.add("홍사라");
		al.add(a2);
		ArrayList<String> a3 = new ArrayList<String>();
		a3.add("C조");
		a3.add("박병준");
		a3.add("전정호");
		a3.add("이경선");
		al.add(a3);
		ArrayList<String> a4 = new ArrayList<String>();
		a4.add("D조");
		a4.add("최현제");
		a4.add("서강인");
		a4.add("염무원");
		al.add(a4);
		System.out.println(al);
	}
}
	 