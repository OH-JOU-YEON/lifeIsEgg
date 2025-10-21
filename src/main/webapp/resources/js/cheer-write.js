function createCheer() {

 const params = {
          userId: userId, 
          content: editor.getHTML(),
          diaryUuid: diaryUuid, 
          cheerUuid: cheerUuid,
        };
 
 console.log(params); 


$.ajax({
    type : 'post',           // 타입 (get, post, put 등등)
    url : './create/cheer',           // 요청할 서버url
    async : true,            // 비동기화 여부 (default : true)
    headers : {              // Http header
      "Content-Type" : "application/json",
      "X-HTTP-Method-Override" : "POST"
    },
    dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
    data : JSON.stringify(params),
    success : function(result) { // 결과 성공 콜백함수
        location.href = "./feed"  //응원작성완료했으면 마저 피드로 돌아감 
    },
    error : function(request, status, error) { // 결과 에러 콜백함수
        console.log(error)
    }
})

}

//POST 요청 시 인가 필요: csrf 토큰을 헤더에 추가
$(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
      beforeSend: function(xhr) {
        xhr.setRequestHeader(header, token);
      }
    });
});
