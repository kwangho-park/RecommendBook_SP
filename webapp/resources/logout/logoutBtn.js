

/* logout 버튼 클릭시 동작하는 logic*/   
function logoutBtn(){
	 
 
  var input = confirm("로그아웃 하시겠습니까?");
                
  if(input){
	 
  	location.href = "/RecommendBook/logout/filter";
    
  	alert("로그아웃 되었습니다")
	  
  }else{
	alert("로그아웃이 취소 되었습니다")
  }
}// logoutBtn