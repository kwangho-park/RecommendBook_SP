<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>

<head>

  <title> login </title>
  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset = "utf-8">
  <meta name="description" content = "사용자의 취향을 고려한 도서 추천 웹 사이트">
  <meta name="keywords" content = "book">
  
  <!-- 전체 영역 단위 CSS -->
  <link href="<c:url value='/common/webSiteStyle.css'/>" type="text/css" rel="stylesheet">

  
  <!-- 해당 page단위 CSS -->
  <link href="<c:url value='/login/loginStyle.css'/>" type="text/css" rel="stylesheet">

  

</head>

<%-- 로그인 필터링 결과를 경고창으로 출력 --%>
<c:choose>
	<c:when test="${requestScope.loginFilterFlag == true}">
		<script>alert("로그인 성공!")</script>
	</c:when>
	<c:when test="${requestScope.loginFilterFlag == false}">
		<script>alert("아이디 또는 비밀번호가 일치하지 않습니다!")</script>
	</c:when>
</c:choose>



<body onload="initLogin()">

  <!-- main penel -->
  <div class="mainPanel">		

  <header>  
  	<h1> Recommend book web site </h1>
  </header>

  <nav>
  	<!-- menu panel -->  
	<%@ include file="/WEB-INF/views/common/menuPanel.jspf"%>
  </nav>

  </div>



  <section>
    
    <article>
    <h2>로 그 인</h2>
    </article>


	<article>
   		<form name = "loginClient" method = "post" action="<c:url value = '/login/filter'/>" onsubmit= "return login()">
   		
    	<label class="loginInfo"> ID </label><input type = "text" 		name="id" id = "id" placeholder="문자">
    	<label class="loginInfo"> PW </label><input type = "password"	name="pw" id = "pw" placeholder="문자 또는 숫자">
    	<br><br>
	   	<div align="center"><input type="submit" value="로그인" style='width:230px;height:40px'></div>

		</form>
	</article>




	<article>
	
	<br>
	<div style='text-align:center'>
		<a href='javascript:loginKakao()'>
			<img src='/RecommendBook/login/img/login.png'/>
		</a>	
	</div>
	
	</article>


  </section>




  <hr>
  
  
  <!-- reference contents -->
  <aside> 
    참고 : 박광호의 기술 블로그<a href = "http://solt.tistory.com" target = "_blank" title = "극논리주의 짠백이">
    (solt.tistory.com) </a>
  </aside>		


  <!-- 저작권 정보, 연락처 등-->
  <footer> 
    저작권 : recommend book web site는 <mark>박광호(웹 프로그래머)에게 저작권</mark>이 있습니다
  </footer>


  <!-- ----------------------------------------------------------------------------------------- -->


  <!-- kakao open API link -->
  <script src="http://developers.kakao.com/sdk/js/kakao.min.js"></script>




  <!-- web page loading 시 초기화 로직 -->
  <script src="<c:url value='/login/initLogin.js'/>"></script>
  
  <!-- 로그인 유효성 검사 로직  --> 
  <script src="<c:url value='/login/loginValidation.js'/>"></script>
  
  <!-- menu setting -->
  <script src="<c:url value='/common/setMenuPanel.js'/>" ></script>
  

  <!-- 카카오 로그인 로직 -->
  <script src="/RecommendBook/login/loginKakao.js"></script>



</body>

</html>