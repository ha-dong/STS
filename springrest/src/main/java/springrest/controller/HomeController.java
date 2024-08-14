package springrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//GET //home 요청하면 listRestBoard라는 뷰의 이름을 리턴 => viewResolve에 전달
	// 앞에 /restboard/ 붙이고 뒤에 .html 붙여서 뷰를 검색
	@GetMapping(value="/home")
	public String home() {
		return"listRestBoard";
	}
}
