import java.net.InetAddress;

public class net1 {
//네트워크 기초

	public static void main(String[] args) throws Exception {
		//InetAddress : IPnetworkAddress
		//getByName : 접속할 도메인 주소명을 말합니다.
		
	
		//InetAddress ia = InetAddress.getByName("localhost");
	 	//System.out.println(ia);
	
		//getAllByName : 접속하는 도메인의 IP를 모두 확인
		InetAddress ia[] = InetAddress.getAllByName("naver.com");
		System.out.println(ia.length);
		int w = 0;
		while(w<ia.length) {
			System.out.println(ia[w]);
			w++;
		}
	}

}
