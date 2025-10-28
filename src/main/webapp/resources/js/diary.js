

function publicOrNot() {

	const publicParam = document.querySelector("#publicOrNot"); 

	if(publicParam.innerText == '일기 공개하기') {
		publicParam.innerText = '일기 비공개하기'; 
	} else {
		publicParam.innerText = '일기 공개하기'; 
	}
}


//POST 요청 시 인가 필요: csrf 토큰을 헤더에 추가(jsp에 meta 추가하기!!!)
$(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
      beforeSend: function(xhr) {
        xhr.setRequestHeader(header, token);
      }
    });
});


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
			"X-HTTP-Method-Override" : "POST",
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
			"X-HTTP-Method-Override" : "POST",
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
	let now = document.querySelector("#created_at").value; //update시 편집중인 날짜(create시 null)
	console.log("now: " + now);
	let old;
	$("#created_at").on("click", function(){ //created_at 바뀌면 인식
		old = $(this).val();
		if (!old) return; //null일 시 종료
	});
	
    $("#created_at").on("change", function(){
		var created_at = $(this).val();
		if (!created_at) return; //null일 시 종료
		console.log("now: " + now);
		console.log("created_at: " + created_at);
		if (created_at == now) return; //편집 중인 날짜를 선택하면 변화 없도록
		
		$.ajax({
    		type : 'get',
    		url : '/lifeEgg/write/date',
    		dataType : 'json',
    		data: { date : created_at },
    		success : function(result) { // 결과 성공 콜백함수
    			if (result && result.user && result.exists) { //post 존재하는지 확인
    				var uuid = result.uuid;
        			const isSure = confirm(`${created_at}의 일기를 불러옵니다. 지금 작성 중인 내용은 저장되지 않습니다.`);
        			if (isSure)
        	    		location.href = '/lifeEgg/write/' + uuid;
        	    	else {
        	    		$("#created_at").val(old);
        	    	}	
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

flatpickr.localize(flatpickr.l10ns.ko); 
$(document).ready(function(){
    flatpickr("#created_at", {
        dateFormat: "Y-m-d",
        onDayCreate: function (dObj, dStr, fp, dayElem) {
        	console.log("writtenDates" + writtenDates);
            const dateStr = fp.formatDate(dayElem.dateObj, "Y-m-d");
            if (writtenDates.includes(dateStr)) {
            	console.log("확인용");
                dayElem.className += " has-action";
            }
        }
    });
});




