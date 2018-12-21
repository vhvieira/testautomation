package com.wipro.tutorial.at.lifecycle;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.ScenarioType;

public class TestLifecycle {

	@BeforeScenario(uponType = ScenarioType.ANY)
	public void setupScenario() {
		System.out.println("# SETUP THE EXECUTION FOR A SCENARIO #");
	}
	
	@AfterScenario(uponType = ScenarioType.ANY, uponOutcome = Outcome.ANY)
	public void tearDownScenario() {
		System.out.println("# TEAR DOWN THE EXECUTION FOR A SCENARIO #");
	}
	
	@BeforeStory
	public void setupStory() {
		System.out.println("# SETUP THE EXECUTION FOR A STORY #");
	}
	
	@AfterStory
	public void tearDownStory() {
		System.out.println("# TEAR DOWN THE EXECUTION FOR A STORY #");
	}
	
	@BeforeStories
	public void setupStories() {
		System.out.println("# SETUP THE EXECUTION FOR A COLLECTION OF STORIES #");
	}
	
	@AfterStories
	public void tearDownStories() {
		System.out.println("# TEAR DOWN THE EXECUTION FOR A COLLECTION OF STORIES #");
	}
}