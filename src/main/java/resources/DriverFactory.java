package resources;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public WebDriverWait w;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		String webDriverURL = System.getProperty("user.dir") + "\\src\\main\\java\\Drivers\\";

		// Call to the data.properties file
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		// Instance the Browser and suppress notifications
		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", webDriverURL + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		}

		else if (browserName.contains("firefox")) {
			System.setProperty("webDriver.gecko.driver", webDriverURL + "geckodriver.exe");

			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		w = new WebDriverWait(driver, 5000);

		return driver;
	}

}
