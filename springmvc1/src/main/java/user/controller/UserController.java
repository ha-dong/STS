package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import user.domain.User;
import user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUser.do")
	public ModelAndView getUser(@RequestParam String name) throws Exception{
		userService.getUser(5);
		User user = new User();	
		return new ModelAndView("/jsp/userView.jsp", "user", user);
		
//		model-controller - View
//		
//		1. DispatcherServlet(Front Controller)
//		2. Controller
//		
//		Dao(Model)-Service-Controller(Controller)-ModelAndView-View(View)
	}
}//class
