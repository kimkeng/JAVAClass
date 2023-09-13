import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udp_client {
//	UDP - client 통신
	public static void main(String[] args) {
		client_udp cu = new client_udp();
		cu.cudp();
	}

}
class client_udp{
	private String ip = null;
	private int port = 0;
	private int myport = 0;
	public DatagramSocket ds = null;	//udp socket
	public DatagramPacket dp = null; 
	public InetAddress ia = null;	//ip 
	public BufferedReader br = null;
	public String msg = null;
	public InputStreamReader in = null;
	
	public client_udp() {
		this.ip = "192.168.110.218";
		this.port = 7000;
		//this.myport = 7001;
		
		//랜덤을 이용한 다주 접속
		this.myport = (int)Math.ceil(Math.random()*1000)+10000;
	}
	public void cudp() {
		try {
			this.ia = InetAddress.getByName(this.ip);	//Server IP를 가져옴	
			this.ds = new DatagramSocket(this.myport);	//자신의 port에 대한 소켓을 오픈
			int ck = 0;
			String id = "";
			String pw = "";
			
			while(true) {
				if(ck == 0) {
					System.out.println("아이디를 입력하세요.");				
				}else {					
					System.out.println("메세지를 입력하세요 : ");				
				}
				this.br = new BufferedReader(new InputStreamReader(System.in));
				this.msg = this.br.readLine();
				byte by[] = this.msg.getBytes();
				//DatagramPacket(byte배열,배열크기,서버ip, 서버port)
				this.dp  = new DatagramPacket(by,by.length,this.ia,this.port);	//Server러 해당 메세지를 패킷을 이용해서 전송
				this.ds.send(dp);	//서버전송
				System.out.println("메세지 전송 완료!!");
			//서버에서 전송된 값을 받는 역활
			byte[] by2 = new byte[200];
			this.dp = new DatagramPacket(by2, by2.length);
			this.ds.receive(this.dp);
			System.out.println(new String(this.dp.getData()));
			ck++;
			}
		}catch(Exception e) {
			System.out.println("server Error");
		}
	}
}