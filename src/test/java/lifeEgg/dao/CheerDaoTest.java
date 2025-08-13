package lifeEgg.dao;

import java.util.UUID;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifeEgg.dao.CheerDAO;
import com.lifeEgg.dao.UserDAO;
import com.lifeEgg.dto.CheerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class CheerDaoTest {
	
	@Autowired 
	CheerDAO cheer; 
	
	
	@Test 
	public void cheerCreateTest() throws Exception {
		
		CheerDTO cheerDTO = new CheerDTO();
		cheerDTO.setContent("테스트");
		cheerDTO.setUuid(UUID.randomUUID().toString());
		
		log.info(cheerDTO.getContent().toString());
		
		
		cheer.create(cheerDTO); 
	}

}
