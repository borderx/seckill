package org.seckill.dao.cache;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

	@Resource
	private RedisDao redisDao;
	
	@Resource
	private SeckillDao seckillDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testSeckill() {
		long seckillId = 1001;
		//get and put
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			if(seckill != null) {
				String result = redisDao.putSeckill(seckill);
				logger.info("result={}", result);
				seckill = redisDao.getSeckill(seckillId);
			}
		}
		logger.info("seckill={}", seckill);
	}


}
