package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class contactsTabForm {

	public WebDriver driver;

	private By salutation = By.xpath("//input[@name='salutation']");
	private By salutationOption = By.xpath("//lightning-base-combobox-item[@data-value='Mr.']");
	private By accountName = By.xpath("//input[@placeholder='Search Accounts...']");
	private By accountNameOption = By.xpath("//li[@role='presentation']");
	private By save = By.xpath("//button[@name='SaveEdit']");
	private static final String textField = "(//div[@class='slds-form-element__control slds-grow']/input)[$index]";

	public contactsTabForm(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSalutation() {
		return driver.findElement(salutation);
	}

	public WebElement getSalutationOption() {
		return driver.findElement(salutationOption);
	}

	public WebElement getTextField(String pIndex) {
		WebElement input = driver.findElement(By.xpath(textField.replace("$index", pIndex)));
		return input;
	}

	public WebElement getAccountName() {
		return driver.findElement(accountName);
	}

	public List<WebElement> getAccountNameOptions() {
		return driver.findElements(accountNameOption);
	}

	public WebElement getSaveButton() {
		return driver.findElement(save);
	}

}
