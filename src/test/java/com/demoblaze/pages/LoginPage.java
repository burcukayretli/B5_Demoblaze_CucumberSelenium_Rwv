package com.demoblaze.pages;

import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    @FindBy(id = "loginusername")
    public WebElement l_loginusername;
    @FindBy(id = "loginpassword")
    public WebElement l_loginpassword;
    @FindBy(css = "button[onclick='logIn()']")
    public WebElement l_loginBtn;

    public void login(){
        String username= ConfigurationReader.get("username");
        String password= ConfigurationReader.get("password");
        l_homePage_loginBtn.click();
        l_loginusername.sendKeys(username);
        l_loginpassword.sendKeys(password);
        l_loginBtn.click();
    }
    public void login(String username,String password){
        l_homePage_loginBtn.click();
        l_loginusername.sendKeys(username);
        l_loginpassword.sendKeys(password);
        l_loginBtn.click();
    }

    public void verifyWrongCredentailsPopUpMessage(String exceptedMessage){
        alert=wait.until(ExpectedConditions.alertIsPresent());
        alert= Driver.get().switchTo().alert();
        //alert.accept();
        String actualMessage= alert.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertEquals(exceptedMessage,actualMessage);
    }
}
