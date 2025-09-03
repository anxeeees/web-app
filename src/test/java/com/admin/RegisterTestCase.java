package com.admin;

import com.admin.pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTestCase {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void registerTestCase(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register("Marek Dol", "heslo", "marek@seznam.cz");
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }


    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

}
