package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class accountTabForm {
	public WebDriver driver;

	private By textField = By.xpath("//div[@class='slds-form-element__control slds-grow']/input");
	private By textArea = By.xpath("//textarea[@class='slds-textarea']");
	private By textFieldLabel = By.xpath("//label[@class='slds-form-element__label slds-no-flex']");

	private By comboBox = By.xpath("//lightning-combobox[@class='slds-form-element_stacked slds-form-element']/label");
	
	private By calendar = By.xpath("//input[@name='SLAExpirationDate__c']");
	private By year = By.xpath("//select[@class='slds-select']");
	private By month = By.xpath("//h2[@data-index]");
	private By nextMonth =  By.xpath ("//button[@title='Next Month']");
	
	private By save = By.xpath("//button[@name='SaveEdit']");
	
	private By alertDisplay = By.xpath("//h2[@title='We hit a snag.']");
	
	private By alertFieldDisplay = By.xpath("//div[@class='slds-form-element__help']");

	private static final String combosLabels = "//label[.='$LabelName'] /following-sibling::div //lightning-base-combobox-item[$ComboOption]";

	public accountTabForm(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSaveButton() {
		return driver.findElement(save);
	}
	
	//Text Inputs 
	public List<WebElement> getTextFieldlabels() {
		return driver.findElements(textFieldLabel);
	}
	public List<WebElement> getTextFields() {
		return driver.findElements(textField);
	}

	//Text Areas
	public List<WebElement> getTextAreas() {
		return driver.findElements(textArea);
	}

	//Combo Boxes management
	public List<WebElement> getComboBoxes() {
		return driver.findElements(comboBox);
	}
	public WebElement getComboOption(String pComboLabelName, String pComboOption) {
		String comboLabel = combosLabels;
		comboLabel = comboLabel.replace("$LabelName", pComboLabelName);
		comboLabel = comboLabel.replace("$ComboOption", pComboOption);
		WebElement option = driver.findElement(By.xpath(comboLabel));

		return option;
	}

	//Calendar Management
	public WebElement getCalendar() {
		return driver.findElement(calendar);
	}
	
	public WebElement getMonth() {
		return driver.findElement(month);
	}
	
	public WebElement getNextMonth() {
		return driver.findElement(nextMonth);
	}
	
	public WebElement getYear() {
		return driver.findElement(year);
	}
	public void manageCalendar(String pMonth, String pYear, String pDay, String pMonthNumber) {
		getCalendar().click();
        Select sel=new Select(getYear());
        String date = pYear+ "-"+ pMonthNumber +"-"+ pDay;

        while(!getMonth().getText().equalsIgnoreCase(pMonth)) {
            getNextMonth().click();
        }
        sel.selectByValue(pYear);
        driver.findElement(By.xpath("//td[@data-value='"+ date +"']")).click();
    }
	
	
	public WebElement getAlertFieldDisplay() {
		return driver.findElement(alertFieldDisplay);
	}
	
	public WebElement getAlertContainerDisplay() {
		return driver.findElement(alertDisplay);
	}

	//JS Executors
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void moveAndClick(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

}
