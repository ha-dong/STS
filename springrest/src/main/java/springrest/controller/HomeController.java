package springrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//GET //home ��û�ϸ� listRestBoard��� ���� �̸��� ���� => viewResolve�� ����
	// �տ� /restboard/ ���̰� �ڿ� .html �ٿ��� �並 �˻�
	@GetMapping(value="/home")
	public String home() {
		return"listRestBoard";
	}
}
