package PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class appPage {
	// Rp - Reports
	// Dsb - Dashboard

	public WebDriver driver;

	private static By tabs = By.xpath("//*[@class='slds-grid slds-has-flexi-truncate']/nav/div/one-app-nav-bar-item-root");
	private By cancelButton = By.xpath("//button[@name='CancelEdit' or @title='Cancel']");
	private By newButton = By.cssSelector("a[title='New']");
	private By toastMessage = By.xpath("//div[@data-key='success']");

	// For Reports and Dashboard
	private By cancelButtonRpDsb = By.xpath("//button[contains(text(),'Cancel')]");
	private By newButtonRpDsb = By.xpath("//div[@title='New Report' or @title='New Dashboard']");
	
	private static final String moreOptionsOptions = "//li[@role='presentation']/a[@title='$option']";

	private static final String tabPath = ".//one-app-nav-bar-item-root[@class='navItem slds-context-bar__item slds-shrink-none'][a[span[text()='$tabName']]]";
	private static final String aHrefTabPath = "//a[@title='$tabName']";
	private static final String iFramePath = "//iframe[@title='$Frame']";
	private static final String moreOptionsMenu = "(//div[@data-aura-class='forceVirtualAction'])[$index]";
	
	

	public appPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> getTabs() {
		return driver.findElements(tabs);
	}

	public WebElement getNewButton() {
		return driver.findElement(newButton);
	}

	public WebElement getCancelButton() {
		return driver.findElement(cancelButton);
	}

	public WebElement getNewButtonRpDsb() {
		return driver.findElement(newButtonRpDsb);
	}

	public WebElement getCancelButtonRpDsb() {
		return driver.findElement(cancelButtonRpDsb);
	}

	public WebElement getiFrame(String pTitle) {
		WebElement iFrame = driver.findElement(By.xpath(iFramePath.replace("$Frame", pTitle)));
		return iFrame;
	}

	public WebElement getTab(String ptabName) {
		WebElement tab = driver.findElement(By.xpath(tabPath.replace("$tabName", ptabName)));
		return tab;
	}
	
	public WebElement getAHrefTab(String ptabAHref) {
		WebElement tabAHref = driver.findElement(By.xpath(aHrefTabPath.replace("$tabName", ptabAHref)));
		return tabAHref;
	}

	public WebElement getMoreOptionsMenu(String pIndex) {
		WebElement menu = driver.findElement(By.xpath(moreOptionsMenu.replace("$index",pIndex)));
		return menu;
	}
	
	public WebElement getMoreOptionsOption(String pOption){
		WebElement option = driver.findElement(By.xpath(moreOptionsOptions.replace("$option", pOption)));
		return option;
	}
	
	public WebElement getToastMessage() {
		return driver.findElement(toastMessage);
	}

	
	public void windowManagement(String pWindow) {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String ParentId = it.next();
		String ChildId = it.next();
		if (pWindow.equalsIgnoreCase("Parent")) {
			driver.switchTo().window(ParentId);
		} else if (pWindow.equalsIgnoreCase("Child")) {
			driver.switchTo().window(ChildId);
		}
	}
	
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		js.executeScript("arguments[0].click();", element);
	}
}
