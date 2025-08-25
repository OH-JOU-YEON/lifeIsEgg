package lifeEgg.dao;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dao.CheerDAO;
import com.lifeEgg.dto.CheerDTO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class CheerDaoTest {
	
	@Autowired 
	CheerDAO cheer; 
	
	CheerDTO cheerDTO = new CheerDTO();
	
	@Test
	public void cheerDaoLogTest() {
		
		
		log.info(cheer.toString());
		
	}


	
	
	
	
	@Test
	public void cheerDtoLogTest() {
		cheerDTO.setContent("테스트");
		cheerDTO.setUuid(UUID.randomUUID().toString());
		
		log.info(cheerDTO.getContent().toString());
		
	}

	
	
	@Test 
	public void cheerCreateTest()  {
		
		cheerDTO.setContent("테스트");
		cheerDTO.setUuid(UUID.randomUUID().toString());
		
		
		 
			try {
				cheer.create(cheerDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}

}
