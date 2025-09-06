package lifeEgg.dao;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
public class UserDaoTest {
	
	@Autowired
	private UserDAO userDao;
	
	//삽입은 1회만 할 것
//	@Test
//    public void insertDataTest() throws Exception {
//		UserDTO user = new UserDTO();
//		user.setEmail("이메일");
//		user.setName("테스트");
//		user.setAge(99);
//		userDao.insertUser(user);
//		System.out.println(user.getId()); //insert와 동시에 id값 받아오는것 확인
//    }
	
	@Test
    public void findByUserIdSuccssesTest() throws Exception {
		
		UserDTO user = new UserDTO();
		Long id = userDao.findUserIdByEmail("이메일");
		log.info(user.toString());
    	Optional<UserDTO> optionalUser = userDao.findUserById(id);
    	user = optionalUser.orElseThrow(() -> new IllegalArgumentException("id: "+ id + "user not exist"));
    	log.info(user.toString());

    }
	
	@Test
    public void findByUserIdFailTest() throws Exception {	
		Long id = userDao.findUserIdByEmail("aaa");
    	System.out.println(id);
    	
    }
}