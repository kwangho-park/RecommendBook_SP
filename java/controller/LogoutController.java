package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		
		return "/logout/logout";
		
	} // logout() END
	
	
	// 로그아웃 시 //
	@RequestMapping(value="/logout/filter", method=RequestMethod.GET)
	public String logoutFilter(HttpServletRequest request, HttpServletResponse response) {


		
		Cookie[] cookies = request.getCookies();

		for(int loop=0 ; loop < cookies.length ; loop++ ) {
			if(cookies[loop].getName().equals("loginFlag")) {
				
				Cookie loginFlag = new Cookie("loginFlag","");	// 제거 = 덮어씌우기
				
				loginFlag.setMaxAge(0);
				loginFlag.setPath("/");							// root경로에 저장된 cookie를 제거
				
				response.addCookie(loginFlag);	
			}
			
		}

		
		return "/login/login";
		
	}// logoutFilter() END
	
}// LogoutController END
