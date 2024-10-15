package com.example.demo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class CustomerJobExecutionListener implements JobExecutionListener{

	StopWatch stopWatch = new StopWatch();
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("JOb Started");
		stopWatch.start();
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("JOb ended");
		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
		
	}

}
