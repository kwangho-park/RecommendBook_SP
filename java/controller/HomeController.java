package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// @RequestMapping("/index")
@Controller
public class HomeController {


	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		
		return "/index/index";
	}
		
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index() {

		return "/index/index";
	}
	

}
