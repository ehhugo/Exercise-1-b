package stepDefinitions;

import java.io.IOException;

import java.util.Hashtable;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import PageObjects.LoginPage;
import PageObjects.accountTabForm;
import PageObjects.appPage;
import PageObjects.contactsTabForm;
import PageObjects.mainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import resources.DriverFactory;

@RunWith(Cucumber.class)
public class stepDefinition extends DriverFactory {

	// #### BACKGROUND STEP DEFINITONS ####

	@Given("initialize browser")
	public void initialize_browser() throws IOException {
		driver = initializeDriver();
	}

	@And("navigate to the Salesforce page {string}")
	public void navigate_to_the_salesforce_page(String pUrl) {
		driver.get(pUrl);
	}

	@When("User enters username {string} and password {string} and logs in")
	public void user_enters_username_and_password_and_logs_in(String pUser, String pPass) {
		LoginPage lp = new LoginPage(driver);
		lp.getUser().sendKeys(pUser);
		lp.getPass().sendKeys(pPass);
		lp.getButton().click();
	}

	// #### Common Steps ####

	@Given("User selects the application {string}")
	public void user_selects_the_application_application(String pApp) {
		mainPage mp = new mainPage(driver);
		w.until(ExpectedConditions.visibilityOf(mp.getWaffle()));
		mp.getWaffle().click();

		w.until(ExpectedConditions.visibilityOf(mp.getWaffleMenu()));

		for (int i = 0; i < mp.getwaffleApps().size(); i++) {
			String lookedForApp = mp.getwaffleApps().get(i).getText();
			System.out.println(lookedForApp);
			if (lookedForApp.equalsIgnoreCase(pApp)) {
				mp.getwaffleApps().get(i).click();
			}
		}
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".slds-card.homeMyDayHeroChart")));
	}

	@Then("clicks on the Accounts tab")
	public void clicks_on_the_accounts_tab() {
		appPage ap = new appPage(driver);
		ap.getTab("Accounts").click();
		w.until(ExpectedConditions.visibilityOf(ap.getNewButton()));
	}

	@And("clicks on the new button")
	public void clicks_on_the_new_button() {
		appPage ap = new appPage(driver);
		ap.getNewButton().click();
		w.until(ExpectedConditions.visibilityOf(ap.getCancelButton()));
	}

	@And("clicks on the save button")
	public void clicks_on_the_save_button() throws InterruptedException {
		accountTabForm at = new accountTabForm(driver);
		at.getSaveButton().click();
	}

	@And("close the Browser")
	public void close_the_browser() {
		driver.quit();
	}

	// #### 1- Iteration between tabs in the Salesforce App ####

	@Then("iteration through all the tabs shown clicking in the new and cancel button of the window")
	public void iteration_through_all_the_tabs_shown_clicking_in_the_new_and_cancel_button_of_the_window()
			throws InterruptedException {
		appPage ap = new appPage(driver);

		int appPageTabs = ap.getTabs().size();

		if (appPageTabs > 0) {

			// iteration through the tabs
			for (int i = 0; i < appPageTabs; i++) {
				ap.getTabs().get(i).click();
				Thread.sleep(3500);

				String pageTitle = driver.getTitle();

				System.out.println(pageTitle);
				// Click on "new Button"
				if (!pageTitle.contains("Home") && !pageTitle.contains("Reports")
						&& !pageTitle.contains("Dashboards")) {

					ap.getNewButton().click();
					w.until(ExpectedConditions.visibilityOf(ap.getCancelButton()));
					ap.getCancelButton().click();
				}

				// evaluates if the pageTitle contains reports or Dashboards and executes the
				// steps
				if (pageTitle.contains("Reports") || pageTitle.contains("Dashboard")) {
					ap.getNewButtonRpDsb().click();

					if (pageTitle.contains("Reports")) {
						driver.switchTo().frame(ap.getiFrame("Report Builder"));
					} else {
						driver.switchTo().frame(ap.getiFrame("dashboard"));
					}
					w.until(ExpectedConditions.visibilityOf(ap.getCancelButtonRpDsb()));
					ap.getCancelButtonRpDsb().click();
					driver.switchTo().defaultContent();
				}
			}

		}

	}

	// #### 2- Account Record creation ####

	@Then("fills the form completing all the fields")
	public void fills_the_form_completing_all_the_fields() throws InterruptedException {
		accountTabForm at = new accountTabForm(driver);

		// ###### HASHTABLE CREATION #####

		String[] dataInputs = new String[] { "ehhugo", "12345678", "123456789", "123456", "http://www.google.com",
				"http://www.google.com", "Rahul Shetty Field", "0", "1000", "123456", "11600", "Montevideo",
				"Montevideo", "Uruguay", "12345", "Montevideo", "Montevideo", "Uruguay", "12345", "1" };

		String[] comboOptions = new String[] { "2", "4", "3", "7", "2", "2", "4", "3" };

		// Hashtable for inputs
		Hashtable<String, String> htinputs = new Hashtable<String, String>();
		for (int i = 0; i < at.getTextFieldlabels().size(); i++) {
			String label = at.getTextFieldlabels().get(i).getText();
			htinputs.put(label, dataInputs[i]);
		}
		// Hashtable for comboboxes
		Hashtable<String, String> htComboOptions = new Hashtable<String, String>();
		for (int i = 0; i < at.getComboBoxes().size(); i++) {
			String label = at.getComboBoxes().get(i).getText();
			htComboOptions.put(label, comboOptions[i]);
		}

		// ###### Input ######
		for (int i = 0; i < at.getTextFields().size(); i++) {
			String label = at.getTextFieldlabels().get(i).getText();
			WebElement textField = at.getTextFields().get(i);
			if (htinputs.containsKey(label)) {
				textField.sendKeys(htinputs.get(label));
				System.out.println(label + " - " + htinputs.get(label));
			}
		}

		// ###### Text Area ######
		for (int i = 0; i < at.getTextAreas().size(); i++) {
			at.getTextAreas().get(i).sendKeys("Calle Falsa 1234");
		}

		// ##### code to go to the top of the form #####
		for (int i = 0; i < 2; i++) {
			driver.findElement(By.xpath("//div[@class='actionBody']")).sendKeys(Keys.PAGE_UP);
		}
		Thread.sleep(1000);

		// ######Combo boxes######
		for (int i = 0; i < at.getComboBoxes().size(); i++) {
			String label = at.getComboBoxes().get(i).getText();
			WebElement comboBox = at.getComboBoxes().get(i);
			at.moveAndClick(comboBox);
			Thread.sleep(1000);

			if (htComboOptions.containsKey(label)) {
				String value = htComboOptions.get(label);
				System.out.println(label + " - " + value);

				at.getComboOption(label, value).click();

			}
		}
		// #Calendar (Method declared in the PageObject Package, in the accountTabForm
		at.manageCalendar("December", "2021", "15", "12");

	}

	// #### 3- Account Record creation without filling required fields ####

	@Then("verifies the alert message")
	public void verifies_the_alert_message() {
		accountTabForm at = new accountTabForm(driver);
		if (at.getAlertContainerDisplay().isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Alert Message was displayed successfuly");
		}else {
			Assert.assertTrue(false);
		}
	}

	// #### 4- Contact Record creation in a New Tab #####
	@Then("clicks on the Contacts tab and opens a new tab in the browser")
	public void clicks_on_the_contacs_tab_and_opens_a_new_tab_in_the_browser() {
		appPage ap = new appPage(driver);
		ap.getAHrefTab("Contacts").sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));

	}

	@Then("switches to the new tab")
	public void switches_to_the_new_tab() {
		appPage ap = new appPage(driver);
		ap.windowManagement("Child");
	}

	@Then("fills the required fields")
	public void fills_the_required_fields() {
		contactsTabForm ct = new contactsTabForm(driver);
		ct.getSalutation().click();
		ct.getSalutationOption().click();
		ct.getTextField("2").sendKeys("Hugo");
		ct.getTextField("3").sendKeys("Borgiani");
		ct.getAccountName().click();
		for (WebElement option : ct.getAccountNameOptions()) {
			if (option.getText().equalsIgnoreCase("ehhugo")) {
				option.click();
				break;
			}
		}

	}

	@Then("Saves the record")
	public void saves_the_record() {
		contactsTabForm ct = new contactsTabForm(driver);
		ct.getSaveButton().click();
	}

	@Then("switches to the first tab")
	public void switches_to_the_first_tab() {
		appPage ap = new appPage(driver);
		ap.windowManagement("Parent");
	}

	// #### 5- Account Record Modification #####

	@And("cliks on the more options menu")
	public void cliks_on_the_more_options_menu() {
		appPage ap = new appPage(driver);
		ap.getMoreOptionsMenu("1").click();
	}

	@And("selects the edit option")
	public void selects_the_edit_option() {
		appPage ap = new appPage(driver);
		ap.jsClick(ap.getMoreOptionsOption("Edit"));
	}

	@Then("modifies the Rating, Upsell Opportunity and Type fields")
	public void modifies_the_rating_upsell_opportunity_and_type_fields() throws InterruptedException {
		accountTabForm at = new accountTabForm(driver);

		for (int i = 0; i < at.getComboBoxes().size(); i++) {
			String label = at.getComboBoxes().get(i).getText();
			WebElement comboBox = at.getComboBoxes().get(i);

			if (label.equalsIgnoreCase("Rating") || label.equalsIgnoreCase("Type")
					|| label.equalsIgnoreCase("Upsell Opportunity")) {
				at.moveAndClick(comboBox);
				// comboBox.click();
				Thread.sleep(1000);

				if (label.equalsIgnoreCase("Rating")) {
					at.getComboOption(label, "2").click();
				}
				if (label.equalsIgnoreCase("Type")) {
					at.getComboOption(label, "7").click();
				}
				if (label.equalsIgnoreCase("Upsell Opportunity")) {
					at.getComboOption(label, "2").click();
				}
			}
		}
	}

	@Then("checks that te modification was successful")
	public void checks_that_te_modification_was_successful() {
		appPage ap = new appPage(driver);
		w.until(ExpectedConditions.visibilityOf(ap.getToastMessage()));
		if (ap.getToastMessage().isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Successful Edit");
		}else {
			Assert.assertTrue(false);
		}
	}

	// #### 6- Account Record Modification filling the Employee field with a wrong Value #####

	@Then("Edits the Employee field with the value {string}")
	public void edits_the_employee_field_with_the_value(String pString) {
		accountTabForm at = new accountTabForm(driver);
		for (int i = 0; i < at.getTextFieldlabels().size(); i++) {
			String label = at.getTextFieldlabels().get(i).getText();
			if (label.equalsIgnoreCase("Employees")) {
				at.getTextFields().get(i).sendKeys(Keys.BACK_SPACE);
				at.getTextFields().get(i).sendKeys(pString);
			}
		}
	}

	@Then("verifies that the alert message displayed is {string}")
	public void verifies_that_the_alert_message_displayed_is(String pString) {
		accountTabForm at = new accountTabForm(driver);
		w.until(ExpectedConditions.visibilityOf(at.getAlertFieldDisplay()));
		String alert = at.getAlertFieldDisplay().getText();
		System.out.println(alert);
		if (alert.equalsIgnoreCase(pString)) {
			Assert.assertTrue(true);
			System.out.println("Alert message displayed successfully");
		}else {
			Assert.assertTrue(false);
		}
	}

	// #### 7- - Accounts creation using cucumber data provider #####
	@Then("fills the Account Name {string}, Phone {string}, Account Number {string} and Website {string} fields")
	public void fills_the_account_name_phone_account_number_and_website_fields(String pAccountName, String pPhone,
			String pAccountNumber, String pWebSite) {
		accountTabForm at = new accountTabForm(driver);
		for (int i = 0; i < at.getTextFieldlabels().size(); i++) {
			String label = at.getTextFieldlabels().get(i).getText();
			System.out.println(label);
			
			if (label.equalsIgnoreCase("*Account Name")) {
				at.getTextFields().get(i).sendKeys(pAccountName);
			}
			
			if (label.equalsIgnoreCase("Phone")) {
				at.getTextFields().get(i).sendKeys(pPhone);
			}
			
			if (label.equalsIgnoreCase("Account Number")) {
				at.getTextFields().get(i).sendKeys(pAccountNumber);
			}
			
			if (label.equalsIgnoreCase("Website")) {
				at.getTextFields().get(i).sendKeys(pWebSite);
			}
			
		}
	}
}
