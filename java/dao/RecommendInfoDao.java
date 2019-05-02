package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dto.PostInfoDto;
import dto.RecommendInfoDto;

public class RecommendInfoDao {


	
	private JdbcTemplate jdbcTemplate;
	

	public RecommendInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	
	// 추천정보에 등록된 도서정보를 조회 // 
	public RecommendInfoDto select(String bookName) {
		
		
		RecommendInfoDto result = jdbcTemplate.queryForObject(
			
				"SELECT * FROM recommendInfo WHERE bookName = ?",
				
				new  Object[] {bookName},			// SQL parameter
				
				// rs.next() 역활
				new RowMapper<RecommendInfoDto>() {
					public RecommendInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						
						RecommendInfoDto recommendInfoDto = new RecommendInfoDto();
						
						recommendInfoDto.setAverageScore(rs.getInt("averageScore"));
						recommendInfoDto.setCounter(rs.getInt("counter"));
						
						
						recommendInfoDto.setBookName(bookName);	// DB에서 조회한건아니지만.. 설정의 편의를 위해 셋팅
						
						return recommendInfoDto;
					}
				}
				
		);
				
		return result;
		
	} // select() END
	
	

	
	
	
	// 최초 도서 등록 시
	// recommendInfo table에 도서정보를 등록
	public void insert(PostInfoDto postInfoDto) {
		
		
		jdbcTemplate.update(
				
				"INSERT INTO recommendInfo(bookName, averageScore, counter) VALUES(?,?,1);",
				
				postInfoDto.getBookName(),
				postInfoDto.getScore()
				);
		
		
	} // insert() END
	
	

	
	
	// update recommend Information // 
	public void update(RecommendInfoDto recommendInfoDto) {
		
		jdbcTemplate.update(
				
				"UPDATE recommendInfo SET averageScore=?, counter=? WHERE bookName=?",
				
				recommendInfoDto.getAverageScore(),
				recommendInfoDto.getCounter(),
				recommendInfoDto.getBookName()
		);

	} // update() END
	
	
	
}// RecommendInfoDao END
