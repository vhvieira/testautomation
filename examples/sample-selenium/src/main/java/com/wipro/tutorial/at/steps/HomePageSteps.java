package com.wipro.tutorial.at.steps;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.tutorial.at.pages.HomePage;

@Component
public class HomePageSteps extends AbstractSteps {

	@Autowired
	private HomePage homePage;
	
	@When("User clicks on menu $menu")
	public void clickOnMenu(@Named("menu") String menu) {
		homePage.clickMenu(menu);
	}
}
