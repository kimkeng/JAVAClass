import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client2 {

	public static void main(String[] args) {
		new client_chat().chat();

	}

}
class client_chat{
	private final String ip = "192.168.110.218";	//서버 접속 ip
	final int port = 8009;	//서버 port
	Scanner sc =null;	//메세지입력
	Socket sk = null;	//소켓 통신 오픈
	InputStream is = null;	//서버 메세지를 받는 역활
	OutputStream os = null;	//서버로 보내는 메세지
	String msg = null;	//Scanner에서 입력한 메세지 내용
	String mid = null;	//접속자 id
	String smsg = null;	//서버에 메세지를 담는 변수
	String check = null;	//exit 타이핑 활용
	
	public void chat() {
		try {
			this.sc = new Scanner(System.in);
			System.out.println("아이디를 생성해 주세요 : ");
			this.mid = this.sc.next();
			
			int ck = 0;
			while(true) {	//접속 유지
				this.sk = new Socket(this.ip,this.port);
				this.is = this.sk.getInputStream();
				this.os = this.sk.getOutputStream();
				if(ck == 0) {	//최초 서버 접속시 server에 입장 message 출력
					this.msg = "["+this.mid+"] : 님 입장하였습니다.";
					
				}else {	//사용자가 입력하는 message
					this.sc = new Scanner(System.in);
					System.out.println("메세지 내용을 입력해 주세요 : ");
					this.check = this.sc.nextLine().intern();
					this.msg = "["+this.mid+" : ]" + this.check;	//server로 발송할 문구
				}
				if(this.check == "exit") {	//message에 exit 타이핑시 접속 종료
					this.msg = "["+this.mid+"] : 님 퇴장하였습니다.";
					this.os.write(this.msg.getBytes());
					this.os.flush();
					this.sc.close();
					this.os.close();
					this.is.close();
					this.sk.close();
				}else {
					this.os.write(this.msg.getBytes());	//server로 발송
					this.os.flush();	//메모리를 비워둠
				}
				
				//서버에서 발송한 메세지를 출력하는 파트
				byte data[] = new byte[1024*2];
				int n = this.is.read(data);
				this.smsg = new String(data,0,n);
				System.out.println(this.smsg);
				ck++;
			}
		}catch(Exception e) {
			System.out.println("서버 접속 장애가 발생하여 강제 종료 됩니다.");
		}
	}
}