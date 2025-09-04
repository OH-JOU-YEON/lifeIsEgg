package lifeEgg.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dao.AlarmDAO;
import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.dto.AlarmPageDTO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class AlarmPagingTest {
	
	@Autowired
	 AlarmDAO alarmDAO;
	
	@Test
	public void getPagesByUserIdTest() {
		
		AlarmPageDTO<AlarmDTO> alarmPages = new AlarmPageDTO<>();
		List<AlarmDTO> alarmList = alarmDAO.readByUserId((long)2,alarmPages);
		log.info(alarmList.toString());
		alarmPages.setContentList(alarmList);
		alarmPages.setTotalSize(alarmDAO.getAlarmsCountByUserId((long)2));
		
		log.info(alarmPages.toString());
		
	}

}
