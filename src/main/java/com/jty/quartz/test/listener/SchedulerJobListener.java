package com.jty.quartz.test.listener;


import com.jty.quartz.test.job.JdbcJob;
import com.jty.quartz.test.job.SimpleJob;
import com.jty.quartz.test.job.TestJob1;
import com.jty.quartz.test.job.TestJob2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author jinit
 * @date 2019-11-10
 * @todo TODO
 */
@Component
public class SchedulerJobListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger= LoggerFactory.getLogger(SchedulerJobListener.class);
    JdbcTemplate jdbcTemplate;
    @Value("${job2.cron}")
    String job2Cron;
    String jdbcJobCorn;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        JdbcJobService();
    }

    public void  simpleJobService(){
        //simpleJob  每十秒执行一次，共5次
        SimpleScheduleBuilder smpScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 10);
        JobDetail simpleJobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("smpJob").build();
        Trigger simpleTrigger =  TriggerBuilder.newTrigger().withIdentity("smpJob").
                withSchedule(smpScheduleBuilder).build();
        try{
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.scheduleJob(simpleJobDetail,simpleTrigger);
            scheduler.start();
        }catch (SchedulerException e){
            logger.error("SchedulerException error,name:{},{},{}","smpJob",e,e.getMessage());
        }
    }
    public void job1Service(){
        //job1  每隔30秒执行一次
        ScheduleBuilder sb = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        JobDetail jobDetail = JobBuilder.newJob(TestJob1.class).withIdentity("testJob1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testJob1").
                withSchedule(sb).build();
        try{
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        }catch (SchedulerException e){
            logger.error("SchedulerException error,name:{},{},{}","testJob1",e,e.getMessage());
        }
    }
    public void job2Service(){
        //job2  每隔20秒执行一次
        ScheduleBuilder sb2 = CronScheduleBuilder.cronSchedule(job2Cron);
        JobDetail jobDetail = JobBuilder.newJob(TestJob2.class).withIdentity("testJob2").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testJob2").
                withSchedule(sb2).build();
        try{
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        }catch (SchedulerException e){
            logger.error("SchedulerException error,name:{},{},{}","testJob2",e,e.getMessage());
        }
    }
    public void JdbcJobService(){

        String sql="select cron from t_timing_job where job_name='jdbcJob'";
        try{
            // 返回类型为String(String.class)
            jdbcJobCorn = jdbcTemplate.queryForObject(sql,String.class);
            logger.error("jdbcJob："+jdbcJobCorn);
        }catch (Exception e){
            logger.error("jdbcJobCorn空值{}",e.getMessage());
        }
        //JdbcJob  每隔50秒执行一次
        ScheduleBuilder sb3 = CronScheduleBuilder.cronSchedule(jdbcJobCorn);
        JobDetail jobDetail = JobBuilder.newJob(JdbcJob.class).withIdentity("jdbcJob").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("jdbcJob").
                withSchedule(sb3).build();
        try{
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        }catch (SchedulerException e){
            logger.error("SchedulerException error,name:{},{},{}","jdbcJob",e,e.getMessage());
        }
    }
    @Autowired
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
