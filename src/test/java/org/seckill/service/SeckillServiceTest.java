package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void testGetById() {
		Seckill seckill = seckillService.getById(1000);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void testSeckillLogic() {
		long seckillId = 1001;
		long phone = 13366337611L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()) {
			logger.info("exposer={}", exposer);
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, exposer.getMd5());
				logger.info("seckillExecution={}", seckillExecution);
			} catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			} catch (SeckillException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.warn("exposer={}", exposer);
		}
	}

	@Test
	public void testexecuteSeckillProcedure() {
		long seckillId = 1003;
		long phone = 13366337611L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()) {
			String md5 = exposer.getMd5();
			SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
			logger.info("seckillExecution={}", seckillExecution);
		} else{
			logger.warn("exposer={}", exposer);
		}
		
	}
}
