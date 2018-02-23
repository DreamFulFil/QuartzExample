package dream.blog.practice.quartz.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dream.blog.practice.quartz.config.QuartzConfig;

public class TestMainWithJavaConfig {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(QuartzConfig.class);
		
		((AnnotationConfigApplicationContext)context).close();
	}

}
