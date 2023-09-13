//네트워크 기초 외부 이미지 및 동영상 크롤링

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class net2 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("웹에서 가져올 이미지 주소를 입력하세요 : ");
		String url = sc.nextLine();
		
		URL u = new URL(url);	//URL(Class) : 네트워크 경로를 말함
		URLConnection con = u.openConnection();	//해당 경로를 연결
		
		// indexOf 기능과 비슷함 : -1(연결 불가능)
		int imgsize = con.getContentLength();
		String imgtype = con.getContentType();	//파일 타입
		long date = con.getDate();	//파일 업로드 날짜
		//날짜 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String day = sdf.format(date);
		
		//해당 데이터를 Stream을 이용해서 가져옴
		InputStream is = u.openStream();
		BufferedInputStream bi = new BufferedInputStream(is);
		
		byte[] data = new byte[bi.available()];
		//파일로 저장
		FileOutputStream fo = new FileOutputStream("123.jpg");
		int imgdata = 0;
		while((imgdata=bi.read(data)) != -1) {
			fo.write(data,0,imgdata);
		}
		fo.flush();
		fo.close();
		bi.close();
		is.close();
		System.out.println("해당 정보를 다운로드 완료 하였습니다.");

	}

}
