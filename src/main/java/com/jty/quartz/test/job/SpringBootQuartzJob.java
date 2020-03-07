package com.jty.quartz.test.job;

import com.jty.quartz.test.listener.SchedulerJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jinit
 * @date 2019-11-10
 * @todo TODO
 */
@Configuration
@EnableScheduling
public class SpringBootQuartzJob  {
    private Logger logger= LoggerFactory.getLogger(SchedulerJobListener.class);
    @Scheduled(cron = "0/2 * * * * ?")
    public void run(){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        String now = df.format(new Date());
        logger.info("时间："+ now+"==》SpringBootQuartzJob 执行。。。。。。。。。。。。");
    }
}

