package dream.blog.practice.quartz.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-core.xml")
public class CronJobSchedulerServiceUnitTest {
	
	@Autowired
	private CronJobSchedulerService cronJobSchedulerService;
	
	@Test
	public void testServiceNotNull() {
		assertNotNull(cronJobSchedulerService);
	}
	
	@Test
	public void testScheduleJob() {
		//Arrange
		final String cronExp = "0/5 * * * * ?";
		final boolean expected = true;
		
		//Act
		boolean result = cronJobSchedulerService.setupSimpleCronSchedule(cronExp);
		
		//Assert
		assertEquals(expected, result);
	}
	
}
