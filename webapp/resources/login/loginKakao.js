/**
 *  카카오 로그인 로직 
 *  - 추후 kakao API를 통해 정보를 얻어서 사용예정
 */




// [API] kakao 초기화 (app key)
Kakao.init('f36d7307efb545288f2ec1b5c2bb66e4');
	
function loginKakao(){
	
	

	// [API] 로그인 팝업창 (인자 : call back method) //
	Kakao.Auth.loginForm({
		
		persistAccessToken: true,	
		persistRefreshToken: true,
		
		
		// login success
		success : function(res) {
			
			// kakao API를 통해 사용자 정보 얻기 : 추후 활용 예정
//			userInfo();			

			window.location.href = "/RecommendBook/login/kakao";	// controller 요청 (GET)
		},

		// login fail
		fail : function(err) {
			alert(JSON.stringify(err));
		}
	});


} // loginKakao() END



// [장기적 고민] 동작이상
function userInfo(){
	
     // [API]
	Kakao.API.request({			// 실행하지 못하는 이유는?
			
	 	url: '/v1/user/me',
		
		// API 요청이 성공 할 경우 실행하는 콜백함수 
		success: function(res) {
			
			console.log(res);

			var userID = res.id;      					//유저의 카카오톡 고유 id
			var userEmail = res.kaccount_email;   		//유저의 이메일
			var userNickName = res.properties.nickname; //유저가 등록한 별명

			console.log(userID);
			console.log(userEmail);
			console.log(userNickName);

		},
		
	fail: function(error) {
		console.log(error);
	}
		
	});
	
} // userInfo() END
