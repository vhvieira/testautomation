package com.wipro.tutorial.at.steps.lifecycle;

import org.jbehave.core.annotations.AfterStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountLifeCycle {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${app.url}")
	private String SAMPLE_BANK_URL;
	
	@AfterStory()	
	public void tearDownScenario() {
		restTemplate.delete(SAMPLE_BANK_URL + "api/accounts/all");		
		System.out.println("#### ACCOUNTS DELETED ###");		
	}
}
