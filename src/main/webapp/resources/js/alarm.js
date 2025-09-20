//클릭하면 알람 미리보기에 나온 응원 전문을 읽어오는 함수 

function readCheer() {

 const params = {
         cheerId : cheerId,
        };
 
 console.log(params); 


$.ajax({
    type : 'post',           // 타입 (get, post, put 등등)
    url : './read/cheer',           // 요청할 서버url
    async : true,            // 비동기화 여부 (default : true)
    headers : {              // Http header
      "Content-Type" : "application/json",
      "X-HTTP-Method-Override" : "POST"
    },
    dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
    data : JSON.stringify(params),
    success : function(result) { // 결과 성공 콜백함수
       
    },
    error : function(request, status, error) { // 결과 에러 콜백함수
        console.log(error)
    }
})

}
