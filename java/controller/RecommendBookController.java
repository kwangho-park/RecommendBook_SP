package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.PostInfoDao;
import dao.SearchBookDao;
import dto.PostInfoDto;
import dto.SearchBookDto;
import dto.SearchStateDto;
import service.PostPaging;

@Controller
public class RecommendBookController {

	// DAO
	@Autowired
	SearchBookDao searchBookDao;
	
	@Autowired
	PostInfoDao postInfoDao;
	
	
	// DTO
	@Autowired
	SearchStateDto searchStateDto;
	
	// service
	@Autowired
	PostPaging postPaging;
	
	// menu panel 에서 요청 // 
	@RequestMapping(value="/recommendBook", method = RequestMethod.GET)
	public String recommendBook(HttpServletRequest request, HttpServletResponse response) {

		
		// [aspect] 게시글 리스트 조회 //
		
		
		
		return "/recommendBook/recommendBook";
	}
	
	

	
	// 도서 검색 요청// 
	@RequestMapping(value="/recommendBook/searchBook", method = RequestMethod.POST)
	public String searchBook(HttpServletRequest request, SearchStateDto command, Model model, HttpServletResponse response) {
		
		// 도서검색 // 
		List<SearchBookDto> searchList = searchBookDao.search(command.getBookType(),command.getFavorite(), command.getBookLevel());
		
		model.addAttribute("searchList", searchList);
		

		
		// [aspect] 게시글 리스트 조회 //
		
		
		return "/recommendBook/recommendBook";
		
		
	} // searchBook() END
	
	
}
