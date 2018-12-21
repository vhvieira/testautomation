package com.wipro.tutorial.at;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@RunWith(JUnitReportinRunner)
public abstract class AbstractStories extends JUnitStories {

	private static final String DEFAULT_STORY_TIMEOUT_SECS = "600";

	private ApplicationContext appContext;

	public AbstractStories() {
		appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		configuredEmbedder().embedderControls()
							.doIgnoreFailureInStories(false)
			                .doFailOnStoryTimeout(false)
			                .doGenerateViewAfterStories(true)
			                .doIgnoreFailureInView(false)
			                .doVerboseFailures(true)
							.useStoryTimeouts(DEFAULT_STORY_TIMEOUT_SECS);
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.HTML));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), appContext);
	}
}