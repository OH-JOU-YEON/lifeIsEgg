package lifeEgg.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.lifeEgg.dto.PostPageDTO;
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
			post.setCreated_at(LocalDate.now());
			
			try {
				postDAO.create(post);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<PostDTO> postList = postDAO.readByUserId((long)1, new PostPageDTO<PostDTO>());
		
		log.info(postList.toString());
		
	}
	
	@Test
	public void insertPostTest() {
		
		int userId = 3;
		
		for(int i=0; i<15; i++) {
			PostDTO post = new PostDTO();
			post.setUser_id((long) userId);
			post.setAge(25);
			post.setUuid(UUID.randomUUID().toString());
			post.setContent("테스트용 유저" + userId + " : " + i);
			post.setCreated_at(LocalDate.now());
			
			try {
				postDAO.create(post);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void postListReadByAgeTest() {
		
		List<PostDTO> postList = postDAO.findByAgeRange((long)1, 25, 25);
		
		log.info(postList.toString());
		
		
		int minAge = 25;
		int maxAge = 25;
		
        List<PostDTO> postList2 = postDAO.findByAgeRange((long)1, minAge, maxAge);
		
		log.info(postList2.toString());
		
	}
	
	@Test
	public void postListReadByAgeExcludeOldTest() {
		
		List<PostDTO> postList = postDAO.findByAgeRange((long)1, 25, 25);
		
		log.info(postList.toString());
		
		List<PostDTO> result = postDAO.findByAgeRangeExcludeOld((long)1, 25, 25, postList.subList(0, 2));
		
		log.info(result.toString());
		
	}
	
	@Test
	public void getPostByDateTest() {
		LocalDate created_at = LocalDate.of(2025, 9, 15);
			
		log.info(postDAO.readByCreated((long) 1, created_at).toString()); 
	}

}
