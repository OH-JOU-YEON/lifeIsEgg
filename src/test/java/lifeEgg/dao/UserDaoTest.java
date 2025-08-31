package lifeEgg.dao;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoTest {
	
	@Autowired
	private UserDAO userDao;
	
//	@Test
//    public void insertDataTest() throws Exception {
//		UserDTO user = new UserDTO();
//		user.setEmail("이메일");
//		user.setName("테스트");
//		user.setAge(99);
//		userDao.insertUser(user);
//    }
	
	@Test
    public void findByUserIdSuccssesTest() throws Exception {
		
		UserDTO user = new UserDTO();
		Integer id = userDao.findUserIdByEmail("이메일");
    	System.out.println(id);
    	Optional<UserDTO> optionalUser = userDao.findUserById(id);
    	user = optionalUser.orElseThrow(() -> new IllegalArgumentException("id: "+ id + "user not exist"));
    	System.out.println(user.getAge());
    	System.out.println(user.getEmail());
    	System.out.println(user.getId());
    	System.out.println(user.getName());

    }
	
	@Test
    public void findByUserIdFailTest() throws Exception {	
		Integer id = userDao.findUserIdByEmail("aaa");
    	System.out.println(id);
    	
    }
}