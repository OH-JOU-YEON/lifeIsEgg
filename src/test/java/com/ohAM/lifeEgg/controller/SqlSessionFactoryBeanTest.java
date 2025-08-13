package com.ohAM.lifeEgg.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class SqlSessionFactoryBeanTest {
	
	@Inject
	SqlSessionFactoryBean factoryBean; 
	
	@Test
	public void test() {
		
		log.info(""+ factoryBean); 
	}
	
	@Test
	public void sessionTest() {
		try {
			log.info(factoryBean.getObject().openSession().toString());
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	

}
