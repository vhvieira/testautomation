package com.wipro.tutorial.at;

import java.util.List;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;

public class AllStories extends AbstractStories {

	@Override
	public List<String> storyPaths() {
		List<String> paths = new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()), "**/stories/**/*.story", "");
		return paths;
	}
}