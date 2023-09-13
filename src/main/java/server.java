import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

	public static void main(String[] args) {
		int port = 8009;	//TCP, UDP 포트번호
		ServerSocket ss = null;	//TCP로 서버통신
		Socket sc = null;	//소켓통신으로 접속 허용
		Scanner sn = null;	
		try {
			
			ss = new ServerSocket(port);	//해당 포트를 오픈
			System.out.println("채팅서버 오픈.");
			
			while(true) {	//반복문으로 접속 유지시킴
				sc = ss.accept();	//접속허용
				//값을 받아서 처리 (client -> server)
				InputStream is = sc.getInputStream();
				//값을 보내는 처리 (server -> client)
				OutputStream os = sc.getOutputStream();
				
				//byte 형태로 받음
				byte[] data = new byte[1024];
				int n = is.read(data);	//client 단어를 읽음
				String message = new String(data,0,n);	//byte를 문자로 변환
				System.out.println(message);	//해당 서버에 출력
				
				
				sn = new Scanner(System.in);
				System.out.println("관리자 메세지 : ");	//서버에서 클라이언트로 발송
				String msg = "관리자 : " + sn.nextLine();
				os.write(msg.getBytes());	//byte단위로 전송
				os.flush();	//메모리 최적화
				
			}
		}catch(Exception e) {
			System.out.println("Server Open Error!!");
		}
	}

}
