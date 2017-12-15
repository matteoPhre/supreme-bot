package com.supreme.com.supreme;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supreme.SupremeItem;
import com.supreme.SupremeItem.Section;
import com.supreme.SupremeItem.Size;
import com.supreme.pages.CheckoutPage;
import com.supreme.pages.ConfirmationPage;
import com.supreme.pages.LoginGmail;
import com.supreme.pages.ProductPage;
import com.supreme.pages.SectionPage;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        // item to buy
    	Section section = Section.TSHIRTS;
        String name = "Dog Shit";
        int colorIndex = 1;
        Size size = Size.LARGE;
        SupremeItem supremeItem = new SupremeItem(section, name, colorIndex, size);

        // set up
        log.info("Starting Bot");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver;
        //WebDriver driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\scai\\Desktop\\chromedriver_2.25\\chromedriver.exe");
        driver = new ChromeDriver(options);
        // go to store section
        log.info("Item to buy: {}", supremeItem);
        
        LoginGmail gmail = new LoginGmail(driver);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        gmail.Login(tabs);
        driver.switchTo().window(tabs.get(0));
        
        SectionPage sectionPage = new SectionPage(driver, supremeItem.getSection());

        // select item
        log.info("Selecting item keyword: {}", supremeItem.getName());
        ProductPage productPage = sectionPage.findItem(supremeItem);
        long start = System.nanoTime();

        // select size
        if (supremeItem.getSize() != null) {
            log.info("Selecting size {}", supremeItem.getSize().getValue());
            productPage.selectSize(supremeItem.getSize());
        }

        // add to cart
        log.info("Adding to cart");
        productPage.addToCart();

        // checkout
        log.info("Checking out");
        CheckoutPage checkoutPage = productPage.checkout();

        // fill information
        log.info("Filling in information");
        checkoutPage.fillInfo();

        // process payment
        log.info("Submitting order");
        checkoutPage.processPayment();

        // captcha
        if (checkoutPage.captchaPresent()) {
            log.info("Waiting for captcha to be solved");
            checkoutPage.waitUntilCaptchaSolved();
        }

        // finish
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        log.info(confirmationPage.success() ? "Success!" : "Fail");
        log.info("Time Elapsed: {}s", (System.nanoTime() - start) / 1000000000.0);
//        WebElement myDynamicElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("gbg4")));
//        driver.findElement(By.id("gbg4")).click();
//
//        //press signout button
//        driver.findElement(By.id("gb_71")).click();
    }
}
