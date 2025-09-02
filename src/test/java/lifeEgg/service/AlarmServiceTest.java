package lifeEgg.service;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lifeEgg.dto.AlarmDTO;
import com.lifeEgg.service.AlarmService;

import lombok.extern.slf4j.Slf4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j 
public class AlarmServiceTest {
	
	@Autowired
	 AlarmService alarmService;
	
	@Test
	public void alarmServiceCreateTest() {
		
		for(int i=0; i<10; i++) {
			
			AlarmDTO alarmDTO = new AlarmDTO();
			alarmDTO.setId((long)2);
			alarmDTO.setContent("테스트알림");
			alarmDTO.setUuid(UUID.randomUUID().toString());
			
			alarmService.createAlarm(alarmDTO);
			
		}
		
	}

}
