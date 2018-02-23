package dream.blog.practice.quartz.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dream.blog.practice.quartz.service.CronJobSchedulerService;

public class TestMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-core.xml");
		
		CronJobSchedulerService service = (CronJobSchedulerService)context.getBean("cronJobSchedulerServiceImpl");
//		service.setupSimpleCronSchedule("0/3 * * * * ?");
		service.setupComplexCronSchedule("0/5 * * * * ?");
		
//		((ClassPathXmlApplicationContext)context).close();
	}

}
