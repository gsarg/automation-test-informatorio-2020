package io.github.jschenfeld.automation.selenium.loginTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	private WebDriver webDriver;

	@FindBy(id =  "login")
	private WebElement signIn;
	@FindBy(id = "email")
	private WebElement email;
	@FindBy(id = "passwd")
	private WebElement pass;
	@FindBy(id = "SubmitLogin")
	private WebElement btnSignIn;
	
	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
	}
	
	@Test
	public void realizarLogin() {
		webDriver.get("http://automationpractice.com");
		PageFactory.initElements(webDriver, this);
		signIn.click();
//		PageFactory.initElements(webDriver, this);
		email.sendKeys("pepeargento@gmail.com");
		pass.sendKeys("12345");
		btnSignIn.click();
		
		try {
			webDriver.wait(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void afterMethod() {
		if(null != webDriver) {
			webDriver.close();
		}
	}
}
