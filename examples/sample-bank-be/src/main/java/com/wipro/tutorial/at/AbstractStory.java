package com.wipro.tutorial.at;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class AbstractStory extends JUnitStory {

	private static final String DEFAULT_STORY_TIMEOUT_SECS = "600";

	private ApplicationContext appContext;

	public AbstractStory() {
		appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		configuredEmbedder().embedderControls()
							.doGenerateViewAfterStories(true)
							.doIgnoreFailureInStories(true)
							.doIgnoreFailureInView(true)
							.useThreads(1)
							.useStoryTimeouts(DEFAULT_STORY_TIMEOUT_SECS);
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
				.useStoryPathResolver(new StoryPathResolver() {

					@Override
					public String resolve(Class<? extends Embeddable> embeddableClass) {
						return getStoryPath();
					}
				});
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), appContext);
	}
	
	protected abstract String getStoryPath();
}