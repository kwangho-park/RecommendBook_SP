package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.UserInfoDao;
import dto.UserInfoDto;
import service.LoginFilter;

@Controller
public class LoginController {

	// DAO
	@Autowired
	UserInfoDao userInfoDao;

	// service 
	@Autowired
	LoginFilter loginFilter;

	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "/login/login";
	} // login() END
	
	

	
	// 일반 계정 로그인 시 // 
	@RequestMapping(value="/login/filter", method=RequestMethod.POST)
	public String loginFilter(HttpServletRequest request, UserInfoDto command, HttpServletResponse response) {

		boolean allPostFlag;
		
		String URL;
		
		//[update] 전체조회 -> 사용자가 입력한 id, pw 조회
		// 전체 id, pw 조회하는 로직 실행
		List<UserInfoDto> dto = userInfoDao.select();
		
		
		// service 에서 id, pw 비교하는 로직 setting
		loginFilter.setInputId(command.getId());
		loginFilter.setInputPw(command.getPw());
		loginFilter.setDto(dto);
		
		
		// 알람창 출력을위한 execute + setting // 
		request.setAttribute("loginFilterFlag", loginFilter.filter());
		
		
		if(loginFilter.filter()) {		// 로그인 성공
			
			
			// login Flag
			try {
				Cookie loginFlag = new Cookie("loginFlag",URLEncoder.encode(command.getId(), "UTF-8"));
				
				// loginFlag.setMaxAge(60*60*24*365);   // 유지기간 1년 (defualt : web browser 종료 시 소멸)
				loginFlag.setPath("/");					// [주의] root경로로 지정해야 모든 page에서 접근가능
				
				response.addCookie(loginFlag);
		
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	
		

			allPostFlag = true;
			
			URL =  "/recommendBook/recommendBook";
			
			
		}else {		// 로그인 실패
			
			URL = "/login/login";
			
			allPostFlag = false;
			
		}
		
		
		// [aspect] 실행분기용
		request.setAttribute("allPostFlag", allPostFlag);
		
		return URL;
		
	} // loginFilter() END
	
	
	
	
	
	
	// kakao 계정으로 로그인 시 // 
	@RequestMapping(value="/login/kakao", method=RequestMethod.GET)
	public String loginKakao(HttpServletRequest request, UserInfoDto command, Model model, HttpServletResponse response) {
		

		// login Flag
		try {
			Cookie loginFlag = new Cookie("loginFlag",URLEncoder.encode("kakaoUser", "UTF-8"));
			
			// loginFlag.setMaxAge(60*60*24*365);   // 유지기간 1년 (defualt : web browser 종료 시 소멸)
			loginFlag.setPath("/");					// [주의] root경로로 지정해야 모든 page에서 접근가능
			
			response.addCookie(loginFlag);
			

		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();

		}	
		
		
		// [aspect] 실행분기용
		request.setAttribute("allPostFlag", true);
		
		
		return "/recommendBook/recommendBook";
		
	} // loginKakao() END
	
	
} // LoginController END
