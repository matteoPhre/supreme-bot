package com.supreme.pages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginGmail extends Page {

	private static final By gMailPwdLocator = By.xpath("//input[@type='password']");
	private static final By facebookEmail = By.id("email");
	private static final By facebookPwd = By.id("pass");
	public WebDriver driver;
	
	public LoginGmail(WebDriver driver ){
		super(driver);
		this.driver = driver;
		driver.get("http://facebook.com");
	}
	
	public void Login(ArrayList<String> tabs) {
//		driver.get("https://accounts.google.com");
		driver.switchTo().window(tabs.get(1));
		//driver.get("http://facebook.com");
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        WebElement element = driver.findElement(By.id("identifierId"));
//        element.sendKeys("patasarru10@gmail.com");

		//findElement(facebookEmail).sendKeys("ciaone@scemo.it");
        //wait 5 secs for userid to be entered
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Enter Password
//        WebElement element1 = findElement(gMailPwdLocator);
        //findElement(facebookPwd).sendKeys("fendersp10");
        //findElement(facebookPwd).submit();
        //Submit button

	}
}
