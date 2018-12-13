package com.wipro.victor.TestAutomation.runner;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.junit.JUnitStories;
import com.wipro.victor.TestAutomation.step.ExampleSteps;
 
public class UnitJBehave extends JUnitStories {
 
	public UnitJBehave() {
		super();
		this.configuredEmbedder().candidateSteps().add(new ExampleSteps());
	}
 
	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/wipro/victor/TestAutomation/story/basic_math.story");
	}
}
