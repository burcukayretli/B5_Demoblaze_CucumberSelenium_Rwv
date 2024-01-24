package com.demoblaze.pages;

import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.Driver;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    @FindBy(xpath = "//button[.='Place Order']")
    public WebElement l_placeOrder_btn;
    @FindBy(id = "name")
    public WebElement l_name;
    @FindBy(id = "country")
    public WebElement l_country;
    @FindBy(id = "city")
    public WebElement l_city;
    @FindBy(id = "card")
    public WebElement l_card;
    @FindBy(id = "month")
    public WebElement l_month;
    @FindBy(id = "year")
    public WebElement l_year;
    @FindBy(xpath = "//button[.='Purchase']")
    public  WebElement l_purchase_btn;


    public int removeProduct(String product){
        l_cartMenu.click();
        String productPath="//td[.='"+product+"']";
        String productPricePath= productPath + "/../td[3]";
        String deletePath= productPricePath + "/../td[a]/a";
        //get the price text of the deleted product
        String priceText= Driver.get().findElement(By.xpath(productPricePath)).getText();
        System.out.println("priceText = " + priceText);
        //delete product
        Driver.get().findElement(By.xpath(deletePath)).click();
        BrowserUtils.waitForVisibility(By.xpath(productPath),10);
        return Integer.parseInt(priceText);
    }


    public int removeProduct(String product,String menuName){
        navigateToMenu(menuName);
        String productPath="//td[.='"+product+"']";
        String productPricePath= productPath + "/../td[3]";
        String deletePath= productPricePath + "/../td[a]/a";
        //get the price text of the deleted product
        String priceText= Driver.get().findElement(By.xpath(productPricePath)).getText();
        System.out.println("priceText = " + priceText);
        //delete product
        Driver.get().findElement(By.xpath(deletePath)).click();
        BrowserUtils.waitForVisibility(By.xpath(productPath),10);
        return Integer.parseInt(priceText);
    }
    public  void fillForm(){
        faker=new Faker();
        BrowserUtils.waitFor(1);
        l_name.sendKeys(faker.name().fullName());
        BrowserUtils.waitFor(1);
        l_country.sendKeys(faker.country().name());
        BrowserUtils.waitFor(1);
        l_city.sendKeys(faker.country().capital());
        BrowserUtils.waitFor(1);
        l_card.sendKeys(faker.finance().creditCard());
        BrowserUtils.waitFor(1);
        l_month.sendKeys(String.valueOf(faker.number().numberBetween(1,12)));
        BrowserUtils.waitFor(1);
        l_year.sendKeys(String.valueOf(faker.number().numberBetween(2024,2030)));
        BrowserUtils.waitFor(1);
    }

    @FindBy(xpath = "//p[@class='lead text-muted ']")
    public WebElement l_confirmation;
    @FindBy(xpath = "//button[.='OK']")
    public WebElement l_ok_btn;
    int actualAmount;

    public void finishPurchase_logAmount(){
        BrowserUtils.waitFor(2);
        l_placeOrder_btn.click();
        fillForm();
        l_purchase_btn.click();
        String confirmationText = l_confirmation.getText();
        System.out.println("confirmationText = \n" + confirmationText);
        String[] confirmationArray = confirmationText.split("\n");
        actualAmount = Integer.parseInt(confirmationArray[1].split(" ")[1]);
        BrowserUtils.waitFor(1);
        l_ok_btn.click();
    }

    public void verifyPurchaseAmount(int expectedPurchaseAmount){
        Assert.assertEquals(expectedPurchaseAmount,actualAmount);
        System.out.println("expectedPurchaseAmount = " + expectedPurchaseAmount);
        System.out.println("actualAmount = " + actualAmount);
    }

}
