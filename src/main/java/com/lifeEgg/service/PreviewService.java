package com.lifeEgg.service;

import java.util.StringTokenizer;

public class PreviewService {
	
	//한 줄 미리보기를 얻는 서비스 얘는 빈 주입 필요 없음. static 메서드로 접근! 
	
	public static String getPreview(String content) {
		
		//콘텐츠를 받아서 <태그> </태그> 로 자르고 검사해서 비어있지 않으면 반환한다. 
		
		StringTokenizer contentToken = new StringTokenizer(content,"/<figcaption>((?!<\\/figcaption>))*<\\/figcaption>/gi");
		
		String token = contentToken.nextToken(); 
		
		while(token.matches("/\\s/g")) {
			
			token = contentToken.nextToken(); 
		}
		
		
		
		return token; 
		
		
		
	}

}
