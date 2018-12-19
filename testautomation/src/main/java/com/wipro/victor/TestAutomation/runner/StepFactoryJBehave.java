package com.wipro.victor.TestAutomation.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.AbsolutePathCalculator;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepFinder;
import org.junit.runner.RunWith;

import com.thoughtworks.paranamer.NullParanamer;
import com.wipro.victor.TestAutomation.step.FactoryExampleSteps;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class StepFactoryJBehave extends JUnitStories {
	
	//most used configuration
	private Configuration mostUsedConfiguration = null;

	public StepFactoryJBehave() {
			super();
		}
	
	@Override
	public Configuration configuration() {
		//simple
		return super.configuration();
		//most used
//		return getMostUsedConfiguration();
	}

	private Configuration getMostUsedConfiguration() {
		if(mostUsedConfiguration == null) {
			Configuration configuration= new Configuration() {
			};
 
			// configuration.doDryRun(false); "no dry run" is implicit by using
			// default StoryControls
	 
			// configuration.useDefaultStoryReporter(new ConsoleOutput());
			// deprecated -- rather use StoryReportBuilder
	 
			configuration.useFailureStrategy(new RethrowingFailure());
			configuration.useKeywords(new LocalizedKeywords(Locale.ENGLISH));
			configuration.usePathCalculator(new AbsolutePathCalculator());
			configuration.useParameterControls(new ParameterControls());
			configuration.useParameterConverters(new ParameterConverters());
			configuration.useParanamer(new NullParanamer());
			configuration.usePendingStepStrategy(new PassingUponPendingStep());
			configuration.useStepCollector(new MarkUnmatchedStepsAsPending());
			configuration.useStepdocReporter(new PrintStreamStepdocReporter());
			configuration.useStepFinder(new StepFinder());
			configuration.useStepMonitor(new SilentStepMonitor());
			configuration
					.useStepPatternParser(new RegexPrefixCapturingPatternParser());
			configuration.useStoryControls(new StoryControls());
			configuration.useStoryLoader(new LoadFromClasspath());
			configuration.useStoryParser(new RegexStoryParser(configuration
					.keywords()));
			configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());
			configuration.useStoryReporterBuilder(new StoryReporterBuilder());
			configuration.useViewGenerator(new FreemarkerViewGenerator());
	 
			EmbedderControls embedderControls = configuredEmbedder()
					.embedderControls();
			embedderControls.doBatch(false);
			embedderControls.doGenerateViewAfterStories(true);
			embedderControls.doIgnoreFailureInStories(false);
			embedderControls.doIgnoreFailureInView(false);
			embedderControls.doSkip(false);
			embedderControls.doVerboseFailures(false);
			embedderControls.doVerboseFiltering(false);
			embedderControls.useStoryTimeoutInSecs(300);
			embedderControls.useThreads(1);
			
			mostUsedConfiguration = configuration;
		}
		
		return mostUsedConfiguration;
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new FactoryExampleSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/wipro/victor/TestAutomation/story/basic_math.story");
	}
}