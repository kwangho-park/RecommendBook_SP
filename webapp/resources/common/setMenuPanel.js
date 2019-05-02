

// DOM 객체 로딩 후 실행되는 jQuery method
$(function(){
		
	////////////////////// client 제어 ///////////////////////
	// client측 cookie 셋팅 (path : '/')
	function setCookie(name, value, day){
        var date = new Date();
        date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
        document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';		
	}

	// client측 cookie 삭제 (path : '/')
    	var deleteCookie = function(name) {
        var date = new Date();
        document.cookie = name + "= " + "; expires=" + date.toUTCString() + "; path=/";
    }

	
	////////////////////// server 제어 ///////////////////////
	// cookie 얻기 (path = ?)
  	function getCookie(name){
        var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
        return value? value[2] : null;
    }
    
	
	// client or server측 cookie 삭제		(client : "/", server : "/context/.."
	function clearCookie(name, domain, path){
        var domain = domain || document.domain;
        var path = path || "/";
        document.cookie = name + "=; expires=" + +new Date + "; domain=" + domain + "; path=" + path;

	}
	    



    
    var loginFlag = getCookie("loginFlag");

    
    if(loginFlag == null){	// logout 상태 
    	
    	$(".menuPanel > #login").attr("href","/RecommendBook/login").text("로그인");
    	
    	// link 비활성
    	$(".menuPanel > #recommendBook").bind('click', false);
    	$(".menuPanel > #post").bind('click', false);    	

    }else if(loginFlag != null){				// login 상태

    	$(".menuPanel > #login").attr("href","/RecommendBook/logout").text("로그아웃");
    	
    	// link 활성
    	$(".menuPanel > #recommendBook").unbind('click', false);
    	$(".menuPanel > #post").unbind('click', false);
    	
    }

    // test point
    //console.log("loginFlag 조건값 : "+ loginFlag);

}); // function() END
	  