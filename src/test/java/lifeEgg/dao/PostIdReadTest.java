package lifeEgg.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dao.PostDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class PostIdReadTest {
	
	//새로 추가한 postid만 읽어오는 메서드 테스트 
	
	@Autowired
	PostDAO postDAO;
	
	@Test
	public void postIdReadTest() {
		
		Long postId = postDAO.readPostIdByUuid("f6e3fe4d-fd14-46ae-abcb-28b0b34ffee3");
		
		log.info(postId.toString());
	}

}
