package com.wipro.tutorial.at.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.tutorial.at.context.TestContext;

public abstract class AbstractSteps {
	
	@Autowired
	protected TestContext context;
}
