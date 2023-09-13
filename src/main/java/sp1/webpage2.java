package sp1;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class webpage2 {
	PrintWriter pw = null;
	
	@PostMapping("/fileok.do")
	public void upload(MultipartFile mfile, HttpServletRequest req, Model m) throws Exception {
		String filename = mfile.getOriginalFilename();
		long filesize = mfile.getSize();
		String url = req.getServletContext().getRealPath("/fileup/") + filename;
		//파일저장
		File f= new File(url);	//기존 io
		FileCopyUtils.copy(mfile.getBytes(), f);	//Spring 전용 파일업로드 클래스
		System.out.println("업로드 파일 정상적으로 진행 완료!!");
	}
	
	
	
	//수정완료
	@PostMapping("/product_modifyok.do")
	public String ok_modify(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");
		String pidx = req.getParameter("pidx");
		String pcode = req.getParameter("pcode");
		String pname = req.getParameter("pname");
		String pmoney = req.getParameter("pmoney");
		String psale = req.getParameter("psale");
		String pues = req.getParameter("puse");
		
		product_ok ok = new product_ok();
		String msg = "";
		int result = ok.modify_sql(pidx, pcode, pname, pmoney, psale, pues);
		if(result == 1) {
			msg = "<script>alert('정상적으로 수정 완료 되었습니다.');"
					+ "location.href='./product_list.do'"
					+ "</script>";
		}else {
			msg = "<script>alert('수정 내용이 올바르지 않습니다.');"
					+ "history.go(-1)"
					+ "</script>";
		}
		try {
			this.pw = res.getWriter();
			this.pw.write(msg);
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("올바른 값이 전달되지 않음");
		}
		return null;
	}
	
	//상품 수정 파트(JSTL)
	@GetMapping("/product_modify.do")
	public String view_product(HttpServletRequest req, Model m) {
		String idx = req.getParameter("idx");
		try {
			product_modify pm = new product_modify();
			ArrayList<String> data = pm.view_ok(idx);
			System.out.println(data);
			m.addAttribute("data",data);
		}catch(Exception e) {
			System.out.println("접근 오류");
		}
				
		return "/WEB-INF/jsp/product_modify";
	}
	
	
	@GetMapping("/product_delete.do")//11 삭제 controller 만들기
	public void del_product(HttpServletRequest req, HttpServletResponse res) { //파라매터를 받기위해
		res.setContentType("text/html;charset=utf-8"); //15 delete는 view를 안썻으므로 set이 필요
		try {
			this.pw = res.getWriter();
			String no = req.getParameter("idx");
			product_delete pd = new  product_delete();
			int result = pd.delete_ok(no); //13
			if(result == 1){//14 정상적인 sql 작동
				this.pw.write("<script>"
						+ "alert('정상적으로 삭제 되었습니다.');"
						+ "location.href='./product_list.do';"
						+ "</script>");
			}
			else { //0 sql문법이 올바르게 작동되지 않을 경우
				this.pw.write("<script>"
						+ "alert('올바른 데이터 값이 아닙니다.')"
						+ "location.href='./product_list.do');"
						+ "</script>");
			}
		}
		catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('잘못된 접근 방식 입니다.')"
					+ "history.go(-1)"
					+ "</script>");	
		}
	}
	
	//JSTl로 view page 출력
	//Controller
	@RequestMapping("/product_list.do")
	public String pd_list(HttpServletRequest req, Model m) {
		List<ArrayList<String>> product_data = null; //1
		product_list pl = new product_list(); //4
		int listea = pl.data_ea(); // list.java에서 가지고 온다 데이터 갯수 
		product_data = pl.listdata(); //2차원 배열 실행
		ArrayList<ArrayList<String>> plist = pl.listdata();
		
		req.setAttribute("listea", listea); //controller -> view 출력
		req.setAttribute("plist", plist); //controller -> view 출력
		return "WEB-INF/jsp/product_list";
		
	}
	
	//spring1.html에 넘어온 값을 view를 통해서 핸들링함
	@PostMapping("/spring1ok.do")
	public String product(HttpServletRequest req , HttpServletResponse res, Model m) {
		String pdcode = "";
		String pdname = "";
		try {
			pdcode = req.getParameter("pdcode").intern();
			pdname = req.getParameter("pdname").intern();
			m.addAttribute("code",pdcode);
			m.addAttribute("name",pdname);
		}
		catch(Exception e) {
			System.out.println("파라미터 오류 발생!!");
		}
		return "/WEB-INF/jsp/spring1ok";
	}
	
	//라디오 사용시 intern사용하여 조건완성
	@PostMapping("/spring2ok.do")
	public String agree(HttpServletRequest req, Model m)  {
		//checkbox 사용시 intern() 사용하지 않음
		String ag = req.getParameter("mail").intern();;
		String ad = req.getParameter("ad").intern();

		if(ad=="N") {
			ag = "N";
		}
		else { 
			ad = req.getParameter("ad").intern();	
		}
		if(ag==null) {
			ag = "N";
			System.out.println("동의안함");
		}
		else {
			System.out.println("동의함");
		}
			return null;
		}
	//getter와 setter를 이용해서 값을 받음
	@PostMapping("/spring3ok.do")
	public String user(HttpServletRequest req, Model m)  {
		String mid = req.getParameter("mid");
		String mname = req.getParameter("name");
		
		userdata ud =new userdata(mid,mname);
		
		System.out.println(ud.getMid());
		System.out.println(ud.getMname());
		return null;
	}
	//실제 메일 서비스 + 네이버 naver.com/nate.com 가능  메일서버 (google은 안됨 (ip는막기 때문), hanmail안됨)
	@PostMapping("/spring4ok.do")
	public String mails(HttpServletRequest req , HttpServletResponse res, Model m) {
		String ename = req.getParameter("ename"); //보낸이 
		String email = req.getParameter("email");  //회신 받을 메일
		String etitle = req.getParameter("etitle"); //제목
		String econ = req.getParameter("econ"); //내용
		
		
		/* 실제 메일 API 서버 정보를 입력*/
		String host = "smtp.naver.com";
		String user = "ox161472@naver.com";
		String password = "";
		String to_mail = "ox161472@naver.com";
		
		
		/*API 포트번호 및 TLS 정보를 입력*/
		Properties props = new Properties();
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port",587);
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.debug","true");
		props.put("mail.smtp.socketFactory.port",587);
		props.put("mail.smtp.ssl.protocols","TLSv1.2");
		
		//SMTP 서버에 로그인을 시키기위한 작업(암호화)
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(user, password);
			}
		});// Session,... (javx.mail)
		try {
			//MimeMessage : okcall 발생함 아이디/패스워드, 포트 모두 맞을 경우
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(email,ename));  //보낸이
			//받는 메일 주소
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_mail));
			msg.setSubject(etitle); //제목
			msg.setText(econ); //내용
			Transport.send(msg); //발송
			System.out.println("메일 발송이 정상적으로 되었습니다.");
		}
		catch(Exception e) {
			System.out.println("메일 서버 통신 오류!!");
		}
		
		return null;
	}
	//Controller에서 배열을 JSP (View)로 출력하는형태
	@GetMapping("/spring5ok.do")
	public String datalist(HttpServletRequest req, Model m) {
		//dbconfig db = new dbconfig();
		//try {
		//Connection ct = db.info();
		//System.out.println(ct.toString());
		//}
		//catch(Exception e) {
			
		//}
		String data[] = {"이순신", "홍길동","강감찬","이산","한석봉"};
		ArrayList<String> al= new ArrayList<String>(Arrays.asList(data));
		//JSP방식 -> 
		//req.setAttribute("person_list", al);
		//return "/WEB-INF/jsp/spring5ok";  //일반 JSP view

		//표현식
		m.addAttribute("person_list",al);// 표현식 방식
		m.addAttribute("peson_delete","10");
		
		//표현식 값을 javascript 전달 (Front-end) Node형태로 출력
		return "/WEB-INF/jsp/spring5_2ok"; //표현식 JSP view
	}
	//사용자 리스트 출력 Mysql 이용
	@RequestMapping("/spring6ok.do")
	public String userlist(HttpServletRequest req, Model m) {
		String search = req.getParameter("search");
		String part = req.getParameter("part");
		//System.out.println(search);
		List<ArrayList<String>> member_data = null;
		try {
			if(search=="null" || search==null ||search== "") {
				user_list ul = new user_list();
				member_data= ul.listdata();
			}
			else { //검색 단어가 있을 경우 
				user_list ul = new user_list();
				member_data= ul.listdata(search,part);
			}
			//회원가입자 카운팅 
			req.setAttribute("total", new user_list().total_member()); // ->JSP에서 get으로 받기
			
			req.setAttribute("member_data", member_data);
			req.setAttribute("part", part);  //검색카테고리 --> 이후 jsp에 변수추가
		}
		catch(Exception e ) {
			
		}
		return "/WEB-INF/jsp/member_list";
		
	}
}







