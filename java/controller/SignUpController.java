package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.UserInfoDao;
import dto.UserInfoDto;



@Controller
public class SignUpController {

	@Autowired
	UserInfoDto userInfoDto;
	
	@Autowired
	UserInfoDao userInfoDao;
	
	
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	public String filter(HttpServletRequest request, HttpServletResponse response) {
		
		return "/signUp/signUp";
	}

	
	
	
	@RequestMapping(value="/signUp/filter", method = RequestMethod.POST)
	public String signUp(UserInfoDto command, Model model, HttpServletResponse response) {
	
	
		boolean result = false;

		
		// 회원가입을 위한 setting //
		userInfoDto.setAll(
				command.getId(), command.getPw(), command.getId(), command.getBirthday(), command.getEmail01(),
				command.getEmail02(), command.getPostCode(), command.getRoadAddress(), command.getJibunAddress(), command.getDetailAddress(),
				command.getExtraAddress()
		); 
		
		// id 중복조회 및 회원 가입 // 
		if(!userInfoDao.filter(command.getId())) {				// id 중복조회
			userInfoDao.insert(userInfoDto);		
			result = true;
		}
	
		
		// browser 경고창 출력을 위한 setting	
		model.addAttribute("signUpFilterResult", result);
		
		return "/signUp/signUp";
	}
		

	
} // SignUpController END
