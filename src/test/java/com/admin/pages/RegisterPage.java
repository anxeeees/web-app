package com.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage
{
    private WebDriver driver;

    private By username = By.xpath("//input[@id='fullname']");
    private By password = By.xpath("//input[@id='password']");
    private By email = By.xpath("//input[@id='email']");
    private By registrationButton = By.xpath("//input[@value='Register']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://localhost:8080/registration");
    }

    public void register(String user, String pass, String mail) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(registrationButton).click();
    }
}
