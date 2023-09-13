import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Thread 활용 => TCP통신
public class server3 {

	public static void main(String[] args) {
		server3 sv = new server3();
		sv.start();
	}
	
	//소켓을 오픈하는 역할 (접속환경)
	public void start() {
		//port는 하나이므로 접속자가 여러명일때 가상 port를 이용하여 계속적으로 추가할 수 있도록 합니다.
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("[채팅서버 오픈]");
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket);
				//client 접속 (client 마다 새로운 스레드 생성)
				chatroom chatroom = new chatroom(socket);	
				chatroom.start();	//Thread (run) 작동!
			}
		}
		catch(Exception e) {
			System.out.println("Thread 오류 발생!");
		}
		finally {			//소켓통신 오류 발생 후 처리하는 방식
			if(serverSocket != null) {
				try {
					serverSocket.close();
					System.out.println("서버를 강제 종료 합니다.");
				}
				catch(Exception e) {
					System.out.println("서버 소켓이 완전 오류 발생!");
					System.exit(0);
				}
			}
		}
	}
}

//접속자 현황을 셋팅해서 Thread로 관리
class chatroom extends Thread{
	InputStream is = null;
	OutputStream os = null;
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	//서버 메모리에 사용자 리스트를 저장하는 공간
	static List<PrintWriter> list = new ArrayList<PrintWriter>();
	
	
	//즉시실행
	public chatroom(Socket socket) {
		this.socket = socket;
		try {
			this.out = new PrintWriter(this.socket.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.list.add(this.out);
			System.out.println(this.socket.getInetAddress());	//접속 상대방 아이피주소
			System.out.println(this.socket.getKeepAlive());	//소켓 유/무 메소드 true / false(Default)
		}
		catch(Exception e) {
			System.out.println("소켓통신 오류!!");
		}
	}
	
	//Thread 실행
	
	/*
	 sendAll - method : List배열에 담긴 모든 접속자에게 해당 메세지를 출력 시키는 역활
	 */
	@Override
	public void run() {
		String name ="";
		try {
			name = this.in.readLine();
			System.out.println("["+name+"]");
			sendAll("["+name+"] Wellcome :) ");
			
			while(this.in != null) {	//Message를 입력하면 작동되는 반복문
				
				String inputmsg = this.in.readLine().intern();
				if(inputmsg=="exit") {
					break;
				}
				else {
					//전체 발송
					sendAll("["+name+"] : " + inputmsg);
				}
			}
		}
		catch(Exception io) {
			System.out.println("---- ["+name+"] Error!! ---- ");
		}
		finally {
			sendAll("["+name+"] Chatting Out!!");
			this.list.remove(this.out);	//접속 해제시 배열에 값을 삭제함
			try {
				this.socket.close();
			}
			catch(Exception e) {
				System.out.println("채팅 서버 종료!");
			}
		}
		System.out.println("----- ["+name+"] EXIT!! -----");	//server에서 해당 id 접속해제를 확인함
	}
	
	//사용자 리스트 정보가 있는 배열을 이용하여 메세지 출력
	private void sendAll(String s) {
		for(PrintWriter out:this.list) {
			out.println(s);
			out.flush();
		}
	}
}