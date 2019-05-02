package aspect;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dao.PostInfoDao;
import dto.PostInfoDto;
import service.PostPaging;

// 전체 게시글 list를 출력하는 aspect bean //
@Aspect
public class LoginAspect {
	
	// Dao
	@Autowired
	PostInfoDao postInfoDao;
	
	// service 
	@Autowired
	PostPaging postPaging;
	
	
	// 로그인 controller point cut 지정 (핵심기능)
	@Pointcut("execution (public * controller.LoginController.*(javax.servlet.http.HttpServletRequest, ..))")
	private void publicTarget() {}
	
	
	
	// 공통기능
	// ProceedingJoinPoint : target class/method 정보를 가진 객체
	@Around("publicTarget()")
	public Object allPost(ProceedingJoinPoint joinPoint) throws Throwable{		
		
		// [장기적 고민] AOP 에서 request객체를 사용하기 위한 설정
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		boolean flag;
	
		
		try {  
			
			return joinPoint.proceed();			// pointcut로 지정된 target객체 호출
			
		}finally {
			
			
			flag = (boolean) request.getAttribute("allPostFlag");

			if(flag) {
				
				int[] searchInfo = new int[6];
				
				
				
				// 현재 page의 post를 출력하기 위한 자원 //
				// client : pageNum, DB : totalCount 
				
				String initPageNum = request.getParameter("pageNum");
				
				int totalCount = postInfoDao.selectTotalCount();
				
				// 초기 recommendBook page 로드 시 parameter가 없는것을 보완
				// [장기적 고민] 코드 가독성을 위해 contoller에서 뺴야할듯...
				int pageNum;
				
				if(initPageNum == null) {		
					pageNum = 1;
					
				}else {
					pageNum = Integer.parseInt(initPageNum);			
				}
				
				
				// PostPaging initialization //
				postPaging.setPageNum(pageNum);
				postPaging.setTotalCount(totalCount);
				
				
				// page에 출력 할 post setting //
				searchInfo = postPaging.searchPost();
				
				List<PostInfoDto> postList = postInfoDao.selectPage(searchInfo);
				
				
				request.setAttribute("postList", postList);

				
				// countPage 연산 및 셋팅 //
				// [추후 업데이트] array ->  hashMap 변환 (set data structure) - 이유 : index는 가독성이 매우 나쁨
				int[] countPage = postPaging.countPage();
				
				
				request.setAttribute("countPage", countPage);
				
				request.setAttribute("pageNum",pageNum);		// 도서 검색시 게시글 출력을 위한 셋팅
			}
		}
		
		
	}// allPost() END
	
	
	
}// LoginAspect END
