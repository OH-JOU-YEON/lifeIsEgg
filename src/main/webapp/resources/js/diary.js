

function publicOrNot() {


const publicParam = document.querySelector("#publicOrNot"); 

if(publicParam.innerText == '일기 공개하기') {

publicParam.innerText = '일기 비공개하기'; 

} else {

publicParam.innerText = '일기 공개하기'; 
}



}

function createOrUpdate() {
	const url = window.location.pathname;
	(url == '/lifeEgg/write') ? createDiary() : updateDiary();
}


function createDiary() {

	console.log("create");

	const params = {
		created_at: document.querySelector("#created_at").value,
		status: document.querySelector("#publicOrNot").innerText,
		age: userAge,
		userId: userId, 
		content: editor.getHTML(),
	};
		
	$.ajax({
		type : 'post',           // 타입 (get, post, put 등등)
		url : '/lifeEgg/create/diary',           // 요청할 서버url
		async : true,            // 비동기화 여부 (default : true)
		headers : {              // Http header
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
		data : JSON.stringify(params),
		success : function(result) { // 결과 성공 콜백함수
		location.href = "/lifeEgg/diaries" 
		},
		error : function(request, status, error) { // 결과 에러 콜백함수
			console.log(error)
		}
	});
}


function updateDiary() {

	console.log("update");

	const uuid = window.location.pathname.replace('/lifeEgg/write/', '');
	console.log(uuid);
	const isPublic = (document.querySelector("#publicOrNot").innerText == '일기 공개하기') ? "true" : "false";
		
	const params = {
		created_at: document.querySelector("#created_at").value,
		status: isPublic,
		age: userAge,
		user_id: userId, 
		uuid: uuid,
		content: editor.getHTML()
	};

	$.ajax({
		type : 'post',           // 타입 (get, post, put 등등)
		url : '/lifeEgg/diary/update',           // 요청할 서버url
		async : true,            // 비동기화 여부 (default : true)
		headers : {              // Http header
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
		data : JSON.stringify(params),
		success : function(result) { // 결과 성공 콜백함수
		location.href = "/lifeEgg/diaries" 
		},
		error : function(request, status, error) { // 결과 에러 콜백함수
			console.log(error)
		}
	});
}


$(document).ready(function () { //화면 준비되면 실행
    $("#created_at").on("propertychange keyup paste input", function(){
		var created_at = $(this).val();
		if (!created_at) return; //null일 시 종료
		console.log(created_at);
		
		$.ajax({
    		type : 'get',
    		url : '/lifeEgg/write/date',
    		dataType : 'json',
    		data: { date : created_at },
    		success : function(result) { // 결과 성공 콜백함수
    			if (result && result.user && result.exists) { //post 존재하는지 확인
    				var uuid = result.uuid;
        			console.log(uuid);
        	    	location.href = '/lifeEgg/write/' + uuid;
                } else if (result && !result.user) {
                	location.href = '/lifeEgg/home';
                }
    		},
    		error : function(request, status, error) { // 결과 에러 콜백함수
        		console.log(error)
    		}
		}); 
	});
});

