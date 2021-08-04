package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver;

	private By user = By.id("username");
	private By pass = By.id("password");
	private By login = By.id("Login");

	// recives a Webdriver type variable
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getUser() {
		return driver.findElement(user);
	}

	public WebElement getPass() {
		return driver.findElement(pass);
	}

	public WebElement getButton() {
		return driver.findElement(login);
	}

}
