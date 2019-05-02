package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.UserInfoDto;

@Service
public class LoginFilter {

	private List<UserInfoDto> userInfoList;
	
	// 사용자 입력 정보 //
	private String loginId;
	private String loginPw;
	
	// db 정보//
	private String dbId;
	private String dbPw;

	
	public LoginFilter(){}

	
	// 사용자 입력 data와 db data를 비교하는 method //
	public boolean filter() {
		
		boolean result = false;

		
		// forEach 문으로 list의 모든 element를 호출
		for(UserInfoDto userInfoDto:userInfoList) {
			
			dbId = userInfoDto.getId();
			dbPw = userInfoDto.getPw();

			if(dbId.equals(loginId)) {
				if(dbPw.equals(loginPw)){
					result = true;	
					break;
				}	
			}
		}

		return result;
	}// loginFilter() END


	
	
	// getter, setter //

	public List<UserInfoDto> getDto() {
		return userInfoList;
	}


	public void setDto(List<UserInfoDto> dto) {
		this.userInfoList = dto;
	}


	public String getInputId() {
		return loginId;
	}


	public void setInputId(String inputId) {
		this.loginId = inputId;
	}


	public String getInputPw() {
		return loginPw;
	}


	public void setInputPw(String inputPw) {
		this.loginPw = inputPw;
	}


	public String getDbId() {
		return dbId;
	}


	public void setDbId(String dbId) {
		this.dbId = dbId;
	}


	public String getDbPw() {
		return dbPw;
	}


	public void setDbPw(String dbPw) {
		this.dbPw = dbPw;
	}
	
	
	

	
}
