-- 秒杀执行存储过程
DELIMITER $$
--定义存储过程
--参数：in：输入参数  out：输出参数
--row_count():返回上一条修改类型sql（delete,insert,update）的影响行数
CREATE PROCEDURE `seckill`.`execute_seckill`
	(in v_seckill_id bigint, in v_phone bigint,
	 in v_kill_time timestamp, out r_result int)
BEGIN
	DECLARE change_count int DEFAULT 0;
	START TRANSACTION;
	insert ignore into success_killed
	(seckill_id, user_phone, state, create_time)
	values
	(v_seckill_id, v_phone, 0, v_kill_time);
	select row_count() into change_count;
	IF (change_count = 0) THEN
		ROLLBACK;
		SET r_result = -1;
	ELSEIF (change_count < 0) THEN
		ROLLBACK;
		SET r_result = -2;
	ELSE
		UPDATE seckill
		SET number = number - 1
		WHERE seckill_id = v_seckill_id 
			AND end_time >= v_kill_time
			AND start_time <= v_kill_time
			AND number > 0;
		SELECT row_count() into change_count;
		IF (change_count = 0) THEN
			ROLLBACK;
			SET r_result = 0;
		ELSEIF (change_count < 0) THEN
			ROLLBACK;
			set r_result = -2;
		ELSE
			COMMIT;
			SET r_result = 1;
		END IF;
	END IF;
END;
$$
--存储过程定义结束

DELIMITER ;

set @r_result=-3;
--执行存储过程
call execute_seckill(1003,18817573572,now(),@r_result);
--获取结果
select @r_result;