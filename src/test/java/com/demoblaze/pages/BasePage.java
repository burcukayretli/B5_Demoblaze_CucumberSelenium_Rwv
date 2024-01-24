package com.demoblaze.pages;

import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(),this);
    }

    protected Alert alert;

    protected Faker faker;
    protected WebDriverWait wait =new WebDriverWait(Driver.get(), Duration.ofSeconds(10));


    @FindBy(linkText = "Log in")
    public WebElement l_homePage_loginBtn;
    @FindBy(id = "nameofuser")
    public  WebElement l_nameofuser;
    @FindBy(partialLinkText = "Home")
    public WebElement l_homeMenu;
    @FindBy(partialLinkText = "Cart")
    public WebElement l_cartMenu;

    void navigateToMenu(String menuName){
        By l_menu = By.partialLinkText(menuName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(l_menu)).click();
    }
}
