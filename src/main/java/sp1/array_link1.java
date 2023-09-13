package sp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
ArrayList : 중복허용 및 순서를 유지하는 배열형태 - 출력 위주

LinkedList : 순방향, 역순 검색 (양방향) - 검색할 때 유리함

 */
public class array_link1 {

	public static void main(String[] args) {
		//System.currentTimeMillis() : 현재 시스템 시간을 초 단위로 읽음
 		System.out.println(System.currentTimeMillis());  
		
		//LinkList : 중간에 데이터를 사용할 경우, 중간 데이터를 삭제
		Integer a[] = {1,3,6,9,10};
		LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(a));
		//System.out.println(list);
		list.add(3,55);
		//System.out.println(list);
		
		//ArrayList : 순차적으로 데이터, 순차적으로 삭제
		Integer b[] = {1,3,6,9,10};
		ArrayList<Integer> list2 = new ArrayList<Integer>(Arrays.asList(b));
		//System.out.println(list2);
		list2.add(3,55);
		//System.out.println(list2);
		
		/* 반복 데이터 */
		LinkedList<Integer> data1 = new LinkedList();
		ArrayList<Integer> data2 = new ArrayList();
		
		//작업시작시간
		long start = System.currentTimeMillis();
		//데이터를 순차적으로 삽입
		Integer w = 0;
		do {
			data2.add(w);
			w++;
		}while(w <= 10000000);
		
		//값을 2단위 중간 데이터를 삽입
		Integer ww = 0;
		int no = 2;
		do {
			data2.add(no,ww);
			no += 2;
			ww++;
		}while(ww <= 500);
		
		//작업이 모두 끝난시간
		long end = System.currentTimeMillis();
		//총 걸린 시간
		long timer = end-start;
		System.out.println(timer);
	}

}
