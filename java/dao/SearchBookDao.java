package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dto.SearchBookDto;

// recommendBook page에서 추천도서 검색 시 실행
// search post class // 

@Repository
public class SearchBookDao {

	private JdbcTemplate jdbcTemplate;
	

	public SearchBookDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	// client에서 요청한 조건으로 검색하는 로직 // 
	// 분류, 취향, 난이도
	public List<SearchBookDto> search(String bookType, String favorite, String bookLevel) {
		
		
		List<SearchBookDto> results = jdbcTemplate.query(
				
				" SELECT DISTINCT postInfo.bookName, postInfo.writer, recommendInfo.averageScore "

					+ " FROM recommendInfo INNER JOIN postInfo " 
				    
				    + " ON postInfo.bookName = recommendInfo.bookName "

					+ " WHERE bookType=? && favorite=? && bookLevel=? "
					
					+ " ORDER BY averageScore DESC LIMIT 0,5 " ,
					
					
					new RowMapper<SearchBookDto>() {
						
						@Override
						public SearchBookDto mapRow(ResultSet rs, int rowNum) throws SQLException{
							
							SearchBookDto searchPostInfoDto = new SearchBookDto();
							
							searchPostInfoDto.setBookName(rs.getString("bookName"));
							searchPostInfoDto.setWriter(rs.getString("writer"));
							searchPostInfoDto.setAverageScore(rs.getInt("averageScore"));
									
							return searchPostInfoDto;		
						}
					},
					
					bookType,  
					favorite,  
					bookLevel
					);

		
		return results.isEmpty() ? null : results;
		
	}// search() END
	
	
	
}// SearchBookDao END
