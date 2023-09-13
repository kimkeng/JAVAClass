import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class udp_server {
//	UDP - server 통신 1:1
	public static void main(String[] args) {
		server_chat sc = new server_chat();
		sc.udp();
	}

}
class server_chat{
	private String ip = null;	//서버ip
	private int port = 0;	//서버udp포트
	public DatagramSocket ds = null;	//udp socket
	public DatagramPacket dp = null; 	//aptpwl thdtnwls vozlt
	public InetAddress ia = null;	//서버 ip, 접속자 ip 기록
	public BufferedReader br = null;
	String msg = "";	//메세지
	
	public server_chat() {
		this.ip = "192.168.110.218";
		this.port = 7000;
	}
	public void udp() {
		try {
			this.ia = InetAddress.getByName(this.ip); //서버 IP확인
			
			System.out.println("UDP Server Open!");
			this.ds = new DatagramSocket(this.port);	//UDP포트 오픈
			
			System.out.println("채팅시작!!");
			while(true) {
				byte[] by = new byte[200];	//메세지 크기
				
				//client에서 오는 패킷 크기를 정함
				this.dp = new DatagramPacket(by,by.length);
				this.ds.receive(this.dp);	//client에서 보낸 메세지를 서버에서 받는 역활
				this.msg = new String(this.dp.getData());
				System.out.println(this.msg);
								
				/* client로 Message를 server에서 전송하는 형태 */
				System.out.println("메세지를 입력하세요 : ");
				/* client ip, idp port 정보 가져옴 */
				InetAddress ia2 = this.dp.getAddress();
				int port2 = this.dp.getPort();
				this.br = new BufferedReader(new InputStreamReader(System.in));
				this.msg = this.br.readLine();
				byte by2[] = this.msg.getBytes();
				
				this.dp  = new DatagramPacket(by2,by2.length,ia2,port2);
				this.ds.send(this.dp);	//서버에서 클라이언트로 메세지를 전송
			}
		}catch(Exception e) {
			System.out.println("UDP Server Open Error");
		}
	}
}
