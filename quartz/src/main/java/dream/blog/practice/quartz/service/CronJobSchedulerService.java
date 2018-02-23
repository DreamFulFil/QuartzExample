package dream.blog.practice.quartz.service;

import org.quartz.JobKey;

public interface CronJobSchedulerService {
	
	boolean setupSimpleCronSchedule(String cronExp);
	
	boolean setupComplexCronSchedule(String cronExp);
	
	boolean removeCronSchedule(JobKey jobKey);
	
}
