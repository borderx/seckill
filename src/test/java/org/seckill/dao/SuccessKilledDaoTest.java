package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class SuccessKilledDaoTest {

	// 注入Dao实现类依赖
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		long seckillId = 1001L;
		long userPhone = 13366337612L;
		int count = successKilledDao.insertSuccessKilled(seckillId, userPhone);
		System.out.println("insertCount=" + count);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long seckillId = 1001L;
		long userPhone = 13366337612L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
		System.out.println("insertCount=" + successKilled);
	}

}
