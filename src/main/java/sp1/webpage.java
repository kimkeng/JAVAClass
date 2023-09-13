package sp1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller	//interface를 해당 class안에 있는 모든 method에 적용하라는 뜻
public class webpage {
	ModelAndView mv = null;
/*
 @RequestMapping : 가상 파일명 GET,POST 모두 사용 (service)
 @GetMapping : GET 전송
 @POSTMapping : POST 전송
 @PutMapping : Database - insert 전용 (Ajax에만 사용)
 @DeleteMapping : database - delete 전용 (Ajax에만 사용)
 @PatchMapping : database - update 전용 (Ajax에만 사용)
 */
	/*
	 ModelAndView(구식 - Controller Interface) VS Model(신식 - String형태로 변환 addAttribute)
	 Model : @(annotation)을 사용하여 적용하는 사항 + 데이터를 담기 위함
	 View에서 Model에 담겨진 데이터를 자동으로 출력 ${} 형태
	 */
	
	/*	
	 ModelAndView : return 시 jsp는 같은 이름으로 처리
	 
	 setViewName를 사용하지 않을 경우 webpage.xml 에 prefix에 해당
	 view경로를 적어 주셔야 보안상 jsp 강제 입력이 되지 않습니다.
	 
	 setViewName를 사용시 webpage.xml에 prefix에는 /(슬러시)로 처리하고
	 해당 ("view 경로") 를 필수로 입력 하셔야 합니다.
	 */
	
	//로그인
	@PostMapping("/login.do")
	public ModelAndView loginok(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String mid = req.getParameter("mid");
		String mpass = req.getParameter("mpass");
		this.mv = new ModelAndView();
		mv.addObject("userid",mid);
		mv.addObject("userpw",mpass);
		mv.setViewName("/WEB-INF/viewpage/login");
		return mv;
	}
	
	
	//상품검색 (문자 자료형으로 return 사용하여 해당 페이지로 view 출력)
	@GetMapping("/search.do")
	public String search(HttpServletRequest req,HttpServletResponse res,Model m) {
		String sh = req.getParameter("product");
		m.addAttribute("word",sh);
		return "/WEB-INF/viewpage/product";
	}
	
	/* void method는 response를 사용시 해당 view를 찾지 못하는 상황이 발생함 */
	//아이디 및 패스워드 찾기
	@PostMapping("/idsearch.do")
	public void joinok(Model m,HttpServletRequest req) {	//void는 Response를 빼야함 추가로ModelAndView 사용 불가능
		String names = req.getParameter("person");
		System.out.println(names);
		m.addAttribute("user",names);
		System.out.println(m.getAttribute("user"));
	}
	
}