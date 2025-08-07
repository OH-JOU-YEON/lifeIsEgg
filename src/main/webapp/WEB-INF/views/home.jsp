<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HELLO OAuth</title>

</head>

<body>
	<fieldset>
		<label>로그인</label> <br>
		<div id="googleLoginBtn" style="cursor: pointer">
			<img id="googleLoginImg" src="./images/btn_google_signin_light_pressed_web.png">
		</div>
	</fieldset>
</body>

<script>
 	const onClickGoogleLogin = (e) => {
    	//구글 인증 서버로 인증코드 발급 요청
 		window.location.replace("https://accounts.google.com/o/oauth2/v2/auth?
        client_id=yourClientID
        &redirect_uri=http://localhost:8090/lifeEgg/login
        &response_type=code
        &scope=email%20profile%20openid
        &access_type=offline")
 	}
	
	const googleLoginBtn = document.getElementById("googleLoginBtn");
	googleLoginBtn.addEventListener("click", onClickGoogleLogin);
    
</script>
</html>
