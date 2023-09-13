package sp1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//index url 메인
public class index implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = "홍길동";
		System.out.println(name);
		ModelAndView mv = new ModelAndView();
		//request.setAttribute("name", name);
		
		//addObject("별명",데이터값) : 표현식 ${}형태 출력 및 getAttribute도 사용가능
		mv.addObject("name",name);	//setAttribute
		
		//View file load
		//viewpage에 .jsp 사용안하는 이유는 dispatcher-servlet.xml에 이미 suffix로 셋팅하였음
		mv.setViewName("WEB-INF/viewpage/index");	
		
		return mv;
	}

}
