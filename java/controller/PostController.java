package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.PostInfoDao;
import dao.RecommendInfoDao;
import dto.PostInfoDto;
import dto.RecommendInfoDto;
import service.AverageScoreCal;

@Controller
public class PostController {

	// DTO
	@Autowired
	PostInfoDto postInfoDto;
	
	@Autowired
	RecommendInfoDto recommendInfoDto;
	
	@Autowired
	RecommendInfoDto recommendInfoDto_before;
	
	
	// DAO
	@Autowired
	PostInfoDao postInfoDao;
	
	@Autowired
	RecommendInfoDao recommendInfoDao;
	
	@Autowired
	AverageScoreCal averageScoreCal;
	
	
	// menu panel 요청 (단순 mapping)// 
	@RequestMapping(value="/post", method=RequestMethod.GET)
	public String post() {
		
		return "/post/post";
	} // post() END

	
	
	
	 
	// 게시글 저장 요청 //
	@RequestMapping(value="/post/savePost", method=RequestMethod.POST)
	public String savePost(PostInfoDto command, HttpServletRequest request, HttpServletResponse response) {

		
		// 추천 정보를 조회 // 
		recommendInfoDto = recommendInfoDao.select(command.getBookName());
		
		// 추천정보 업데이트 or 삽입 // 
		if(recommendInfoDto != null) {
			
			// service logic
			averageScoreCal.addition(command.getScore(), recommendInfoDto);
		
			
			// DAO
			recommendInfoDao.update(recommendInfoDto);
			
			
		}else {
			recommendInfoDao.insert(command);
		}
		
		// 게시글 정보를 저장 //
		postInfoDao.insertPost(command);
		
		
		// 경고창 출력을 위한 setting
		request.setAttribute("saveSuccess", true);
		
		
		
		// [aspect] 실행 // 
		

		
		return "/recommendBook/recommendBook";
		
	} // savePost() END
	
	
	

	// 게시글 수정 요청 // 
	@RequestMapping(value="/post/modifyPost", method=RequestMethod.POST)
	public String modifyPost(PostInfoDto command, HttpServletRequest request, HttpServletResponse response) {
		
		
		// 게시글 수정 전 data setting // 
		
		postInfoDto = postInfoDao.selectPost(command.getNum());

		String bookName_before = postInfoDto.getBookName();
		int score_before = postInfoDto.getScore();
		
		
		// 추천정보 조회 // 
		recommendInfoDto = recommendInfoDao.select(command.getBookName());
		recommendInfoDto_before = recommendInfoDao.select(bookName_before);
		
		
		// 도서명 변경 시
		if(!command.getBookName().equals(bookName_before)) {
		
			// 추천정보 업데이트 of 삽입
			if(recommendInfoDto != null) {
				
				// service logic
				averageScoreCal.addition(command.getScore(), recommendInfoDto);
			
				
				// DAO
				recommendInfoDao.update(recommendInfoDto);
				
				
			}else {
				recommendInfoDao.insert(command);
			}			
			
			
			
		// 도서명 동일 + 추천점수 변경 시 
		}else if((command.getBookName().equals(bookName_before)) &&(command.getScore() != score_before)) {

			// [service] 
			averageScoreCal.modify(command.getScore(), score_before, recommendInfoDto);
			
			// [dao]
			recommendInfoDao.update(recommendInfoDto);
		}
		
		
		// 게시글 수정 //
		postInfoDao.updatePost(command);
		
		request.setAttribute("modifySuccess", true);
		
		
		
		// [aspect] 실행 //
		

		return "/recommendBook/recommendBook"; 
		
	}// modifyPost() END

	
	
	
	
	
	// 특정(num) 게시글을 삭제 요청 // 
	// 게시글 삭제 + 추천정보 업데이트
	@RequestMapping(value="/post/deletePost", method=RequestMethod.GET)
	public String deletePost(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt((request.getParameter("num")));
		
		
		// 삭제 대상 post정보를 조회 // 
		postInfoDto = postInfoDao.selectPost(num);
		
		String bookName = postInfoDto.getBookName();
		int score		= postInfoDto.getScore();
		
		
		
		// 추천정보 업데이트 대상 조회 // 
		recommendInfoDto = recommendInfoDao.select(bookName);
		
		
		// 추천점수 업데이트 // 
		// 인자로전달한 dto에 직접 접근하여 setting
		averageScoreCal.subtraction(score, recommendInfoDto);
		
		recommendInfoDao.update(recommendInfoDto);		// 에러발생
		
		
		// post 삭제 //
		postInfoDao.deletePost(num);
		
		
		
		request.setAttribute("deleteSuccess", true );	// 경고창 출력용 setting
		
		
		return "/recommendBook/recommendBook";

	} // deletePost() END
	
	
	

	
	// 특정(num) 게시글 조회페이지 요청 //
	@RequestMapping(value="/post/viewPost", method=RequestMethod.GET)
	public String viewPage(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		postInfoDto = postInfoDao.selectPost(num);
		
		// request에 게시글을 정보를 저장하고있는 DTO 셋팅
		request.setAttribute("dto", postInfoDto);
				
				
		return "/post/viewPost";
	} // viewPost() END


	
	
	// 특정(num) 게시글 수정페이지 요청 //
	@RequestMapping(value="/post/modifyPost", method=RequestMethod.GET)
	public String modifyPage(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		postInfoDto = postInfoDao.selectPost(num);
		
		// request에 게시글을 정보를 저장하고있는 DTO 셋팅
		request.setAttribute("dto", postInfoDto);
				
				
		
		return "/post/modifyPost";
		
	} // viewPost() END

	

	
	
} // PostController END
