package dream.blog.practice.quartz.jobs;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ComplexJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			JobDetail jobDetail = context.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			JobDataMap params = jobDetail.getJobDataMap();
			
			for(int i=0;i<5;i++) {
				System.err.println("In Complex Job " + jobKey + 
						", performing task:"+ i + 
						", params:"+ params.getString("name"));
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}		
	}

}
