package com.wipro.tutorial.at.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.tutorial.at.pages.HomePage;
import com.wipro.tutorial.at.pages.LoginPage;

@Component
public class LoginSteps extends AbstractSteps {

	@Autowired
	private LoginPage loginPage;
	
	@Autowired
	private HomePage homePage;
	
	@Given("User is on Login Page")
	public void userGoesLoginPage() {		
		loginPage.navigateTo();
	}
	
	@When("User login as $username with password $password")
	public void userLogin(	@Named("username") String username,
						  	@Named("password") String password) {
		loginPage.username(username)
				.password(password)
				.clickLoginButton();
	}
	
	@Then("User $username must be successful logged in")
	public void assertUserIsLoggedIn(@Named("username") String username) {
		Assert.assertEquals(username, homePage.getUsernameText());
	}
}
