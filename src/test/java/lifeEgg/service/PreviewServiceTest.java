package lifeEgg.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.service.PreviewService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class PreviewServiceTest {
	
	@Test
	public void previewReturnTest() {
		
		String content = "<p>내용을 입력해 주세요.</p>"; 
		
		String preview = PreviewService.getPreview(content); 
		
		log.info(preview);
	}

}
