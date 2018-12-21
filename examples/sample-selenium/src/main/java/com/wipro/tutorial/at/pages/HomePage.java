package com.wipro.tutorial.at.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.wipro.tutorial.at.configuration.pageobjects.PageObject;

@PageObject
public class HomePage extends AbstractPage {

	@FindBy (id = "sb-username")
	private WebElement welcomeLabel;
	
	@FindAll( @FindBy(className = "linkButton") )
	List<WebElement> menuElements;
	
	/**
	 * Returns the name of user that is displayed on top
	 * of Home Page.
	 * @return
	 */
	public String getUsernameText() {
		String welcomeMsg = welcomeLabel.getText();
		Pattern pattern = Pattern.compile(",\\s*([\\w]+)\\s*\\(");
		
		Matcher matcher = pattern.matcher( welcomeMsg );		
		
		String userName = StringUtils.EMPTY;
		
		if (matcher.find()) {
			userName = matcher.group(1); 
		}
				
		return userName;
	}
	
	/**
	 * Click on a side menu. Menu must be visible in order to allow click event.
	 * @param menu Name of the menu that must be clicked.
	 * @return
	 */
	public HomePage clickMenu(String menu) {
		for (WebElement element : menuElements) {
			if (element.isDisplayed() && menu.equals(element.getText()) ) {
				element.click();
				break;
			}
		}
		return this;
	}	
}
