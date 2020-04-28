package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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

    @After
    public void tearDown(){
        driver.close();
    }




    }
