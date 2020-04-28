package org.example;

import static java.lang.Thread.*;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();

    }

    @Test
    public void seleniumExampleTest() throws InterruptedException{
        driver.manage().window().maximize();
        sleep(2000);
        driver.get("http://www.google.co.uk");
        WebElement googleSearchBar = driver.findElement(By.name("q"));
        googleSearchBar.sendKeys("funny dog pics");
        sleep(1000);
        System.out.println("");
        googleSearchBar.submit();
        WebElement linkToPictures = driver.findElement(By.partialLinkText("Images for funny dog"));
        linkToPictures.click();
        sleep(1000);
        WebElement imagesLink =driver.findElement(By.className("NZmxZe"));
        assertTrue(imagesLink.isDisplayed());
    }

    @Test
    public void task() throws InterruptedException{
        driver.manage().window().maximize();;
        sleep(1000);
        driver.get("http://thedemosite.co.uk/login.php");
        WebElement addUser = driver.findElement(By.partialLinkText("Add a User"));
        addUser.click();
        sleep(1000);
        WebElement usernameAddUser = driver.findElement(By.name("username"));
        usernameAddUser.sendKeys("user");
        WebElement passwordAddUser = driver.findElement(By.name("password"));
        passwordAddUser.sendKeys("root");
        WebElement submitButton = driver.findElement(By.name("FormsButton2"));
        submitButton.click();
        sleep(1000);

        WebElement login = driver.findElement(By.partialLinkText("Login"));
        login.click();
        sleep(1000);
        WebElement usernameLogin = driver.findElement(By.name("username"));
        usernameLogin.sendKeys("user");
        WebElement passwordLogin = driver.findElement(By.name("password"));
        passwordLogin.sendKeys("root");
        WebElement submitLogin = driver.findElement(By.name("FormsButton2"));
        submitLogin.click();
        sleep(1000);
        WebElement successfulLogin = driver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
        assertTrue(successfulLogin.isDisplayed());
    }

    @After
    public void tearDown(){
        driver.close();
    }

    }
