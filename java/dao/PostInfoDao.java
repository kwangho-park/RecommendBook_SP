package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dto.PostInfoDto;


// DB postInfo table 에 접근하기 위한 method //

@Repository
public class PostInfoDao {

	
	private JdbcTemplate jdbcTemplate;
	

	public PostInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	

	// 게시글 data 를 db에 저장 // 
	public void insertPost(PostInfoDto postInfoDto) {
		
		// update : set, execute //
		jdbcTemplate.update(
		
				// 첫번째 인자 : SQL
				"INSERT INTO postInfo(bookName, writer, title, content, "
				+ "bookType, favorite, bookLevel, score) VALUES(?,?,?,?,?,?,?,?)" ,
				
				
				// 두번째 인자 : parameter
				postInfoDto.getBookName(), postInfoDto.getWriter(), postInfoDto.getTitle(),
				postInfoDto.getContent(), postInfoDto.getBookType(), postInfoDto.getFavorite(),
				postInfoDto.getBookLevel(), postInfoDto.getScore()

		);
	}

	

	
	// 게시글 리스트를 조회 //
	public List<PostInfoDto> selectAllPost() {
		
		List<PostInfoDto> results = jdbcTemplate.query(
		
				"SELECT * FROM postInfo ORDER BY num ASC",
				
				new RowMapper<PostInfoDto>() {
					
					@Override
					public PostInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						
						PostInfoDto postInfoDto = new PostInfoDto(
								
								rs.getInt("num"),
								rs.getString("bookName"),
								rs.getString("writer"),
								rs.getString("title"),
								rs.getString("content"),
								rs.getString("bookType"),
								rs.getString("favorite"),
								rs.getString("bookLevel"),
								rs.getInt("score")
								);
						
						return postInfoDto;	
					}
				}
				
				); 

			return results.isEmpty() ? null : results ;		// usEmpty true = null 출력
		
	} // selectAllPost() END

	
	
	
	
	
	// 특정 게시글(num)을 조회 //
	public PostInfoDto selectPost(int num) {
	
		PostInfoDto result = jdbcTemplate.queryForObject(
				
				"SELECT * FROM postInfo WHERE num = ?",
				  
				new Object[]{num},			// SQL parameter 
				  
				new RowMapper<PostInfoDto>() {
					public PostInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						PostInfoDto postInfoDto = new PostInfoDto();
						
						postInfoDto.setBookName(rs.getString("bookName"));
						postInfoDto.setWriter(rs.getString("writer"));
						postInfoDto.setTitle(rs.getString("title"));
						postInfoDto.setContent(rs.getString("content"));
						postInfoDto.setBookType(rs.getString("bookType"));
						postInfoDto.setFavorite(rs.getString("favorite"));
						postInfoDto.setBookLevel(rs.getString("bookLevel"));
						postInfoDto.setScore(rs.getInt("score"));
						
						postInfoDto.setNum(num);
						
						return postInfoDto;
				    }
		});
		
		
		return result;
		
	} // selectPost() END
	
	

	
	
	// 특정게시글(num)을 삭제 //
	public void deletePost(int num) {
		
		jdbcTemplate.update(
				
				"DELETE FROM postInfo WHERE num=?",
				
				num
		);
		
	}// deletePost() END
	

	
	
	// 특정(num)게시글을 수정 //
	public void updatePost(PostInfoDto postInfoDto) {
		
		jdbcTemplate.update(
				
				"UPDATE postInfo SET bookName=?, writer=?, title=?, "
				+ "content=?, bookType=?, favorite=?, bookLevel=?, score=? "
				+ "WHERE num = ?",
				
				postInfoDto.getBookName(),
				postInfoDto.getWriter(),
				postInfoDto.getTitle(),
				postInfoDto.getContent(),
				postInfoDto.getBookType(),
				postInfoDto.getFavorite(),
				postInfoDto.getBookLevel(),
				postInfoDto.getScore(),
				postInfoDto.getNum()
		);
		
	} // updatePost() END
	
	
	
	
	
	// 전체 게시글의 수를 조회 (row의 총 갯수)
	public int selectTotalCount() {
		
		String sql = "SELECT count(*) as totalCount FROM postInfo";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return result;
		
	}// selectTotalCount() END
	
	
	
	
	
	// 선택된 page에 출력 할 게시글 조회 // 
	// 전달인자 : 게시글 수, 게시글 시작번호
	public List<PostInfoDto> selectPage(int[] searchInfo) {
		
		
		List<PostInfoDto> results = jdbcTemplate.query(			
		
				"SELECT * FROM postInfo ORDER BY num DESC LIMIT ?, ?",
				
				new RowMapper<PostInfoDto>() {
					
					@Override
					public PostInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException{
						
						PostInfoDto postInfoDto = new PostInfoDto(
								
								rs.getInt("num"),
								rs.getString("bookName"),
								rs.getString("writer"),
								rs.getString("title"),
								rs.getString("content"),
								rs.getString("bookType"),
								rs.getString("favorite"),
								rs.getString("bookLevel"),
								rs.getInt("score")
								);
						
						return postInfoDto;	
					}
				},
				
				searchInfo[0],
				searchInfo[1]
				);
		
		return results.isEmpty() ? null : results ;		
		
	}//selectFirstPage() END
	
	
	
	
	
	
} // PostInfoDao END
