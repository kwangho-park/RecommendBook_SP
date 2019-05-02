<!-- 댓글 작성 페이지 -->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>


<html>
<head>
	<title>comment page</title>

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
    
      
		<!-- 댓글 (comment) -->
		<article>
			<div class="postInfo"> 
			
			<!-- front에서 필터링 없이 server로 전송 -->
			<!-- ////////// post -> get변경 후 게시글 번호를 server에 전달 예정-->
			<form name="comment" action="/RecommendBook/comment/saveComment" method="post">

				<br>
				이름 : <input type="text" name="userName">  pw : <input type="password" name="pw">  <br> <br>
			
				<textarea name="content" cols="50" rows="10" placeholder="이곳에 댓글을 작성해주세요 -"></textarea> <br>
				
			
				<input type="submit" value="댓글 등록"><br>
			
			</form>
			</div>
		<hr>
		
		</article>

  </section>
 
 	
 	
  <!-- menu setting -->
  <script src="<c:url value='/common/setMenuPanel.js'/>" ></script>
  

</body>

</html>