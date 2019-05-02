package dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dto.UserInfoDto;


@Repository
public class UserInfoDao {
	
	private JdbcTemplate jdbcTemplate;
	

	public UserInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	


	
	// login 시 ID, PW를 조회 // 
	// sql로 id 필터링하는것으로 업데이트 예정		(param = test)
	public List<UserInfoDto> select() {
		
		// query : set, execute, return //
		List<UserInfoDto> results = jdbcTemplate.query(	// return type : List<Object> 
				
				// 첫번째 인자 : SQL
				"SELECT id, pw FROM userInfo",
				
				
				// 두번째 인자 : rs.next() 역활
				new RowMapper<UserInfoDto>() {
					
					@Override
					public UserInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserInfoDto userInfoDto = new UserInfoDto(
								
								rs.getString("id"),
								rs.getString("pw")
								);
						return userInfoDto;		
					}
				}
				
				// 세번째 인자 : parameter
				// 없음
				
				);
		
		return results.isEmpty() ? null : results ;		// usEmpty true = null 출력
	}


	
	
	
	
	
	// signUp 시 id 중복 조회 // 
	public boolean filter(String signUpId) {
		
		boolean result = false;
		
		// query : set, execute, return // 
		List<UserInfoDto> userInfoList = jdbcTemplate.query(
			
			"SELECT id FROM userInfo WHERE id = ?",
			
			
			// 두번째 인자 : rs.next() 역활
			new RowMapper<UserInfoDto>() {
				
				@Override
				public UserInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserInfoDto userInfoDto = new UserInfoDto();
					
					userInfoDto.setId(rs.getString("id"));		
					
					return userInfoDto;		
				}
			},
			
			// 세번째 인자 : parameter
			signUpId
			
			);
			
		
		// id 중복 체크//
		
		for (UserInfoDto u : userInfoList) {		// list의 0번째부터 마지막 요소까지 반복 (현재 예제에서는 1개의 행만 출력)
			
			if(signUpId.equals(u.getId())) {
				result = true;	
			}	
		}
	
		return result;
		
	} // filter() END
	
	

	
	// 회원가입 시 데이터 삽입 // 
	public void insert(UserInfoDto userInfoDto) {

		
		// update : set, execute //
		jdbcTemplate.update(
				
				// 첫번째 인자 : SQL
				"INSERT INTO userInfo VALUES(?,?,?,?,?,?,?,?,?,?,?)",
				
				// 두번째 인자 : parameter
				userInfoDto.getId(), userInfoDto.getPw(), userInfoDto.getUserName(), 
				userInfoDto.getBirthday(), userInfoDto.getEmail01(), userInfoDto.getEmail02(),
				userInfoDto.getPostCode(), userInfoDto.getRoadAddress(), userInfoDto.getJibunAddress(),
				userInfoDto.getDetailAddress(), userInfoDto.getExtraAddress()
			
		);
	}

	
} // UserInfoDao END
