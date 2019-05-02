package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {

	
	@RequestMapping(value="/comment/writeComment", method=RequestMethod.GET)
	public String writeComment() {
		
		return "/post/comment/writeComment";
	} // writeComment() END
	
	
	@RequestMapping(value="/comment/saveComment", method=RequestMethod.POST)
	public String saveComment() {
		
		// 댓글정보 +  게시글 번호 전달받아야함
		
		// 댓글 정보를 저장하는 로직 (DAO)
		
		
		// [aspect] 댓글 조회
		
		// [aspect] 해당 게시글 조회
		
		return "/post/viewPost";
	}
	
	
}// CommentController END
