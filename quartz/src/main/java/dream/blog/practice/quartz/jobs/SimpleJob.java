package dream.blog.practice.quartz.jobs;

public class SimpleJob {
	
	public void execute() {
		try {
			for(int i=0;i<5;i++) {
				System.err.println("In SimpleJob, performing task:"+ i);
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
}
