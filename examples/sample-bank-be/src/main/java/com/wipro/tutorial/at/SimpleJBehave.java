package com.wipro.tutorial.at;

import java.util.Arrays;
import java.util.List;
 
import org.jbehave.core.embedder.Embedder;
 
public class SimpleJBehave {
 
	private static Embedder embedder = new Embedder();
	private static List<String> storyPaths = Arrays
			.asList("stories/account/account_transactions.story");
 
	public static void main(String[] args) {
//		embedder.candidateSteps().add(new ExampleSteps());
		embedder.runStoriesAsPaths(storyPaths);
	}
}