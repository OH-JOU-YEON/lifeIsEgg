package lifeEgg.dao;

import java.time.LocalDateTime;
import java.util.List;
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

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class PostDaoTest {
	
	@Autowired
	PostDAO postDAO;
	
	@Autowired
	UserDAO userDAO;
	
	
	
	
	
	@Test
	public void postListReadTest() {
		
		UserDTO user = new UserDTO();
		
		 user.setName("김광훈"); 
		 user.setEmail("kghwaon@gmail.com");
		 user.setAge(20);
		 
		 log.info(user.toString());
		 
		 try {
			userDAO.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		for(int i=0; i<10; i++) {
		PostDTO post = new PostDTO();
		post.setUser_id((long) 1);
		post.setUuid(UUID.randomUUID().toString());
		post.setContent("테스트"+i);
		post.setCreated_at(LocalDateTime.now());
		
		try {
			postDAO.create(post);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		List<PostDTO> postList = postDAO.readByUserId((long)1);
		
		log.info(postList.toString());
		
	}

}
