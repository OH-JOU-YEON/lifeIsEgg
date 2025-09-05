function createCheer() {

 const params = {
          created_at: document.querySelector("#created_at").value,
          age: userAge,
          userId: userId, 
          content: editor.getHTML(),
        };
 
 console.log(params); 


$.ajax({
    type : 'post',           // 타입 (get, post, put 등등)
    url : '/create/cheer',           // 요청할 서버url
    async : true,            // 비동기화 여부 (default : true)
    headers : {              // Http header
      "Content-Type" : "application/json",
      "X-HTTP-Method-Override" : "POST"
    },
    dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
    data : JSON.stringify(params),
    success : function(result) { // 결과 성공 콜백함수
        console.log(result);
    },
    error : function(request, status, error) { // 결과 에러 콜백함수
        console.log(error)
    }
})

}