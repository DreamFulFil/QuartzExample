package dream.blog.practice.quartz.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;

import dream.blog.practice.quartz.jobs.ComplexJob;
import dream.blog.practice.quartz.jobs.SimpleJob;
import dream.blog.practice.quartz.service.CronJobSchedulerService;

@Service
public class CronJobSchedulerServiceImpl implements CronJobSchedulerService {

	@Autowired
	private Scheduler scheduler;
	
	@PostConstruct
	public void init() {
		try {
			scheduler.start();
		}
		catch(SchedulerException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public boolean setupSimpleCronSchedule(String cronExp) {
		final String jobName = "TestSimpleJob";
		
		try {
			//Create Job Detail
			MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
			jobDetail.setTargetObject(new SimpleJob());
	        jobDetail.setTargetMethod("execute");
	        jobDetail.setName(jobName);
	        jobDetail.setConcurrent(false);
	        jobDetail.afterPropertiesSet();
	        
	        //Create Trigger
            CronTriggerFactoryBean  cronTrigger = new CronTriggerFactoryBean();
            cronTrigger.setBeanName("TestSimpleJobTrigger");
            cronTrigger.setCronExpression(cronExp);
            cronTrigger.afterPropertiesSet();
            
            scheduler.scheduleJob(jobDetail.getObject(), cronTrigger.getObject());
		}
		catch(NoSuchMethodException | SchedulerException | ParseException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean setupComplexCronSchedule(String cronExp) {
		final String jobName = "TestComplexJob";
		
		try {
			//Create Job Detail
			JobDetailFactoryBean  jobDetail = new JobDetailFactoryBean ();
			jobDetail.setJobClass(ComplexJob.class);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", "RAM");
			jobDetail.setJobDataAsMap(map);
			jobDetail.setName(jobName);
			jobDetail.afterPropertiesSet();
	        
	        //Create Trigger
            CronTriggerFactoryBean  cronTrigger = new CronTriggerFactoryBean();
            cronTrigger.setBeanName("TestComplexJobTrigger");
            cronTrigger.setCronExpression(cronExp);
            cronTrigger.afterPropertiesSet();
            
            scheduler.scheduleJob(jobDetail.getObject(), cronTrigger.getObject());
		}
		catch(SchedulerException | ParseException ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean removeCronSchedule(JobKey jobKey) {
		// TODO Auto-generated method stub
		return false;
	}

}
