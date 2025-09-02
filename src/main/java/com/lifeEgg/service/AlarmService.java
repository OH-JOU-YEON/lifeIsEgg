package com.lifeEgg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.lifeEgg.dao.AlarmDAO;
import com.lifeEgg.dto.AlarmDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AlarmService {
	
	private final AlarmDAO alarmDAO;
	
	public void createAlarm(AlarmDTO alarmDTO) {
		
		try {
			alarmDAO.create(alarmDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAlarmById(Long id) {
		
		try {
			alarmDAO.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAlarmByUuid(String uuid) {
		
		try {
			alarmDAO.deleteByUuid(uuid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//유저 아이디 별 알람 읽어오는 메서드 
	
	public List<AlarmDTO> getAlarmsByUserId(Long userId) {
		
		List<AlarmDTO> alarmList = alarmDAO.readByUserId(userId);
		
		
		
		return alarmList;
	}
	
	
	public AlarmDTO getAlarmByUuid(String uuid) {
		
		Optional<AlarmDTO> optionalAlarm = alarmDAO.readByUuid(uuid);
		
		return optionalAlarm.orElseThrow(() -> new IllegalArgumentException("alarm who has uuid doesn't exist"));
	}
	
	

}
