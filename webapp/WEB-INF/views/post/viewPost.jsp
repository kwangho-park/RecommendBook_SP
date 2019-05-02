<!-- 게시글 조회 페이지 -->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>


<html>
<head>
	<title>view post</title>

	<meta charset="UTF-8">
	<meta name="description" content = "사용자의 취향을 고려한 도서 추천 웹 사이트">
	<meta name="keywords" content = "book">

    <!-- 전체 영역 단위 CSS -->
    <link href="<c:url value='/common/webSiteStyle.css'/>" type="text/css" rel="stylesheet">

    <!-- 해당 page단위 CSS -->
    <link href="<c:url value='/post/postStyle.css'/>" type="text/css" rel="stylesheet">



</head>



<body>


  	<!-- main penel -->
  	<div class="mainPanel">		
  
  	<header>  
  		<h1 > Recommend book web site </h1>
  	</header>

	  <nav>
	  		<!-- menu panel -->  
			<%@ include file="/WEB-INF/views/common/menuPanel.jspf" %>
	  </nav>
	  
	
	
    </div>
    
    
    
    
    <section>  
		<article>
		
			<!-- 게시글  -->
			
			<h2>게시글 수정</h2>
		
			<div class="postInfo"><label>도서명 : </label>${requestScope.dto.bookName } </div><br>
			<div class="postInfo"><label>작가명 : </label>${requestScope.dto.writer } </div><br>
	
			<div class="postInfo">
			제 목 <br>
			${requestScope.dto.title }
			</div>
			<br>
	
		
			<div class="postInfo">
				내 용<br>
				${requestScope.dto.content }
			</div>
			<br>
		
			<hr>

			<h3>도서 분류</h3>
	
			<div class="postInfo"> 
			 	<label> 1. 분류 : </label> ${requestScope.dto.bookType}
				</div>
			<br>
	  
	  
			<div class="postInfo"> 
			  	<label> 2. 취향 : </label> ${requestScope.dto.favorite }    
			</div><br>
	  
	  
			<div class="postInfo"> 
	  			<label> 3. 난이도 : </label> ${requestScope.dto.bookLevel }
		 		<br>   
	 		
		    <p>* 도서별로 지정된 분류/취향/난이도에 따라 선택해주세요</p>
	    
			</div>
		
		
			<hr>
		
			<h3>추천 점수</h3>
	
			<div class="postInfo"> ${requestScope.dto.score } <br>
				<p>[비추천] 1  <------> 10 [추천]</p>
			</div><br>

			<div class="postInfo">
				<input type="button" value="게시글 수정" onclick="location.href='/RecommendBook/post/modifyPost?num=${requestScope.dto.num}'">	
			</div>
			<br>
		</article>
		
		<hr>
		<hr>
		
		<%--
		
		<article>
			<div class="postInfo"> 
			<!-- 댓글 (comment) -->
			<!-- jquery ajax사용필요 : 페이지의 일부만 로드하는 기능 -->
			
			이름 : <!-- 이름 출력 -->   <input type="button" value="수정"> <input type="button" value="삭제"><br>
			
			<!-- 내용 출력 --><br>
			</div>
		<hr>
		
		</article>
		
		
		<!-- 댓글 작성 버튼 -->
		<div class="postInfo">
		<input type="button" value="댓글 작성" onclick="location.href='/RecommendBook/comment/writeComment'"><br>
		</div>
		
		  --%>


  </section>
 
 	
 	
  <!-- menu setting -->
  <script src="<c:url value='/common/setMenuPanel.js'/>" ></script>
  

</body>

</html>