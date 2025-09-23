package lifeEgg.dao;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dao.PostDAO;
import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.PostDTO;
import com.lifeEgg.dto.UserDTO;
import com.lifeEgg.service.PreviewService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class PostCreateTest {
	
	@Autowired
	PostDAO postDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	
	@Test
	public void createTestPost() {
		
		//테스트 포스트 데이터 10개씩 만드는 테스트 
		Optional<UserDTO> user = userDAO.findUserById((long)1); 
		
		UserDTO userDTO = user.get(); 
		
		int age = userDTO.getAge(); 
		
		for(int i = 0; i< 10; i++) {
			
			PostDTO postDTO = new PostDTO(); 
			postDTO.setUser_id((long)1);
			postDTO.setAge(age); 
			postDTO.setContent("<p>오늘도 안녕하신가요? 괜찮은 하루이길 바라봅니다.</p>");
			postDTO.setPreview(PreviewService.getPreview("<p>오늘도 안녕하신가요? 괜찮은 하루이길 바라봅니다.</p>"));
			postDTO.setUuid(UUID.randomUUID().toString()); 
			postDTO.setStatus(true); 
			
			try {
				postDAO.create(postDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
