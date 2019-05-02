package dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dto.CommentInfoDto;

@Repository
public class CommentInfoDao {

	private JdbcTemplate jdbcTemplate;
	
	public CommentInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 댓글 정보를 DB에 저장 //
	public void insertComment(CommentInfoDto commentInfoDto) {
			
		jdbcTemplate.batchUpdate(
				
				"sql",
				
				"parameter"
				
				
		);
	}
	
	
}// CommentInfoDao END
