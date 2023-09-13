import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server2 {

	public static void main(String[] args) {
		new chat().chatserver();

	}

}
class chat{
	private int port = 8009;	//server port open
	ServerSocket sk = null;	//server socket open 
	Socket so = null;	//socket 통신
	Scanner sc = null;	//message 입력
	OutputStream os = null;	//client에서 message를 받음
	InputStream is = null;	//client에서 message를 발송
	String mid = null;	//id 입력 변수
	String msg = null;	//server가 입력한 message
	String cmsg = null;	//client가 입력한 message
	String check = null;	//exit 타이핑
	
	public void chatserver() {
		try {
			this.sk = new ServerSocket(this.port);
			this.sc = new Scanner(System.in);
			System.out.println("아이디를 생성하세요 : ");
			this.mid = sc.next();
			System.out.println("채팅방 개설 되었습니다.");
			
			for(;;) { //접속 유지
				this.so = this.sk.accept();
				this.is = this.so.getInputStream();
				this.os = this.so.getOutputStream();
				
				//client 내용을 받아오는 역활
				byte data[] = new byte[1024*2];
				int n = this.is.read(data);
				this.msg = new String(data,0,n);
				System.out.println(this.msg);
				
				//message를 client로 보내는 역활
				this.sc = new Scanner(System.in);
				System.out.println("메세지를 입력하세요.");
				this.check = this.sc.nextLine().intern();
				
				if(this.check == "exit") {
					System.out.println("채팅 종료!!");
					this.is.close();
					this.os.close();
					this.so.close();
					this.sk.close();
				}
				this.cmsg = "["+this.mid+"] : " + this.check;
				this.os.write(this.cmsg.getBytes());	//보내기 완료
				this.os.flush();	//메모리를 비워둠
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
