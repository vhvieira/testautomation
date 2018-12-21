package com.wipro.tutorial.at.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wipro.tutorial.at.configuration.pageobjects.PageObject;

@PageObject
public class CreateAccountPage extends AbstractPage {

	@FindBy (id = "ownerCpf")
	private WebElement cpfInput;
	
	@FindBy (className = "sb-button")
	private WebElement createAccountButton;
	
	@FindBy (id = "sb-return-message")
	private WebElement returnMsg;
	
	public CreateAccountPage cpf (String cpf) {
		cpfInput.clear();
		cpfInput.sendKeys(cpf);
		return this;
	}
	
	public CreateAccountPage clickCreateButton() {
		createAccountButton.click();
		return this;
	}
	
	public String getReturnMsg() {
		return returnMsg.getText();
	}
}
