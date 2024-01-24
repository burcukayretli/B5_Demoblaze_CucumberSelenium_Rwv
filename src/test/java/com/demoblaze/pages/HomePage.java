package com.demoblaze.pages;

import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

public class HomePage extends BasePage{

    @FindBy(linkText = "Add to cart")
    public WebElement l_add_to_cart_btn;
    @FindBy(xpath = "//h3[@class='price-container']")
    public  WebElement l_priceText;

    public void verifyWelcomeMessage(){
        BrowserUtils.waitForVisibility(l_nameofuser,10);
        String actualMessage= l_nameofuser.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertTrue(actualMessage.contains(ConfigurationReader.get("username")));
    }
    public void verifyWelcomeMessage(String username){
        BrowserUtils.waitForVisibility(l_nameofuser,10);
        String actualMessage= l_nameofuser.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertTrue(actualMessage.contains(username));
    }

    public int addProduct(String product,String category){
        try {
            WebElement categoryTab = Driver.get().findElement(By.linkText(category));
            BrowserUtils.waitForClickablility(categoryTab,10).click();
        }catch (Exception e){
            BrowserUtils.waitForClickablility(By.linkText(category),10).click();
        }try {
            WebElement productItem = Driver.get().findElement(By.linkText(product));
            BrowserUtils.scrollToElement(productItem);
            BrowserUtils.waitForClickablility(productItem,10).click();
        }catch (Exception e){
            BrowserUtils.waitForClickablility(By.linkText(product),10).click();
        }

        System.out.println("l_priceText = " + l_priceText.getText());
        String[] arrayAmount = l_priceText.getText().split(" ");
        System.out.println("Arrays.toString(arrayAmount) = " + Arrays.toString(arrayAmount));
        int lastPrice= Integer.parseInt(arrayAmount[0].substring(1));

        l_add_to_cart_btn.click();
        alert=wait.until(ExpectedConditions.alertIsPresent());
        alert=Driver.get().switchTo().alert();
        alert.accept();
//        l_homeMenu.click();
        navigateToMenu("Home");
        return lastPrice;
    }
}
