package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class mainPage {

	public WebDriver driver;

	private By waffle = By.cssSelector("[class='slds-icon-waffle']");
	private By waffleMenu = By.xpath("//div[@class='slds-size_medium']");
	private By app = By.xpath("//*[@class='slds-dropdown__item']/a/div/lightning-formatted-rich-text/span/p");

	public mainPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getWaffle() {
		return driver.findElement(waffle);
	}
	
	public WebElement getWaffleMenu() {
		return driver.findElement(waffleMenu);
	}
	
	public List<WebElement> getwaffleApps(){
		return driver.findElements(app);
	}
	

	
}
