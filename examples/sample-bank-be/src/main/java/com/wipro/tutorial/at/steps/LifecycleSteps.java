package com.wipro.tutorial.at.steps;

import org.jbehave.core.annotations.Given;
import org.springframework.stereotype.Component;

@Component
public class LifecycleSteps {

	@Given("a step that is executed before each scenario")
	public void givenSetupScenario() {
		System.out.println("JBehave: Setup the scenario through the story");
	}
	
	@Given("a step that is executed after each scenario regardless of outcome")
	public void givenTearDownOfAnyScenario() {
		System.out.println("JBehave: Tear down of any scenario outcome through the story");
	}
	
	@Given("a step that is executed after each successful scenario")
	public void givenTearDownOfSuccessfullScenario() {
		System.out.println("JBehave: Tear down of success outcome through the story");
	}
	
	@Given("a step that is executed after each failed scenario")
	public void givenTearDownOfFailedScenario() {
		System.out.println("JBehave: Tear down of failed scenario outcome through the story");
	}	
}