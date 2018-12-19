package com.wipro.victor.TestAutomation.runner;

import java.util.Arrays;
import java.util.List;
 
import org.jbehave.core.embedder.Embedder;

import com.wipro.victor.TestAutomation.step.ExampleSteps;
 
public class SimpleJBehave {
 
	private static Embedder embedder = new Embedder();
	private static List<String> storyPaths = Arrays
			.asList("com/wipro/victor/TestAutomation/story/basic_math.story");
 
	public static void main(String[] args) {
		embedder.candidateSteps().add(new ExampleSteps());
		embedder.runStoriesAsPaths(storyPaths);
	}
}