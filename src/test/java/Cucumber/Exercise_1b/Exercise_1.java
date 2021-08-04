package Cucumber.Exercise_1b;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import resources.DriverFactory;

public class Exercise_1 extends DriverFactory {
	
	public WebDriver driver;
	
	public void initialize () throws IOException{
		driver= initializeDriver();
	}

	
	
}
