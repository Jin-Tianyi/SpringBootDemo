package com.jty.quartz.test.job;

import com.jty.quartz.test.listener.SchedulerJobListener;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jinit
 * @date 2019-11-10
 * @todo TODO
 */
public class TestJob2 implements Job {
    private Logger logger= LoggerFactory.getLogger(SchedulerJobListener.class);
    @Override
    public void execute(JobExecutionContext var1) {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        String now = df.format(new Date());
        logger.info("时间："+now+"==》job 2 执行。。。。。。。。。。。。");
    }
}
