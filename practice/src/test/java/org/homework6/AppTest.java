package org.homework6;


import base.commonApi;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppTest extends commonApi {

    @Test
    public void testRadioButton() {
        click("//fieldset/*[(text()='Radio Button Example')]/following::label/input[@name='radioButton']");
        List<WebElement> list = driver.findElements(By.xpath("//fieldset/*[(text()='Radio Button Example')]/following::label/input[@name='radioButton']"));

        for (WebElement element1 : list) {
            element1.click();
            wait(5);
        }


    }

    @Test
    public void suggetionClassExample() {
        // click("//input[@id='autocomplete']");
        type("//input[@id='autocomplete']","can");
        wait(2);
        for(int i = 0;i<4;i++){
            find("//input[@id='autocomplete']").sendKeys(Keys.ARROW_DOWN);
            wait(2);
            if(i==2){
                wait(2);
                find("//input[@id='autocomplete']").sendKeys(Keys.ENTER);
            }
        }


    }

    @Test
    public void dropdownExample() {
        WebElement dropDown = find("//fieldset/legend/following::select");
        Select select = new Select(dropDown);
        //select.selectByVisibleText("Option2");
        //select.deselectAll(); not working
        select.selectByValue("option3");
        List<WebElement> list = getDropDownOptions("//fieldset/legend/following::select");
        for (WebElement element : list) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void checkBoxExample() {
        List<WebElement> list = driver.findElements(By.xpath("//fieldset/legend/following::label/input[@type='checkbox']"));
        for (WebElement element : list) {

                element.click();
            }


          if(find("(//fieldset/legend/following::label/input[@type='checkbox'])[2]").isSelected()){
              find("(//fieldset/legend/following::label/input[@type='checkbox'])[2]").click();
          }

            //why not text
        }



    @Test
    public void switchtoNewTab() {
        String parent = driver.getWindowHandle();
        //  System.out.println(parent);
        click("//a[@id='opentab']");
        Set<String> allWindow = driver.getWindowHandles();
        for (String newTab : allWindow) {
            if (!(parent.equals(newTab))) {
                driver.switchTo().window(newTab);
                wait(5);
                click("//a[text()='Practice'][1]");
                wait(10);
                driver.close();

            }
        }
        driver.switchTo().window(parent);
        System.out.println(driver.getTitle());
        // click("//a[@id='opentab']");
        // System.out.println("number of window is "+allWindow.size());
    }

    @Test
    public void closePopUp() {
        String parent = driver.getWindowHandle();
        click("//button[@id='openwindow']");
        wait(5);
        Set<String> windowsOpened = driver.getWindowHandles();
        ArrayList<String> myTabs = new ArrayList<>(windowsOpened);
        driver.switchTo().window(myTabs.get(1));
        driver.close();
//         for (String newWinodws:windowsOpened) {
//             if(!(parent.equals(newWinodws))){
//                 driver.switchTo().window(newWinodws);
//                 wait(5);
//                 driver.close();
//             }
//         }
//         driver.switchTo().window(parent);
//     }


    }

    @Test
    public void handleAlert() {
      acceptAlert("//input[@id='alertbtn']");

//        System.out.println("alert clicked");

    }

    @Test
    public void handleAlert2() {
       dismissAlert("//input[@id='confirmbtn']");
       // q= dismissed or accepted
    }

    @Test
    public void handleTable() {
//            WebElement e=find("//table[@id='product']/tbody/tr[6]/td[1][contains(text(),'Rahul')]");
//       System.out.println(e.getText());
//       WebElement e1=find("//table[@id='product']/tbody/tr[6]/td[2][contains(text(),'Learn')]");
//       System.out.println(e.getText());
       searchInTable("//table[@name='courses']/tbody","Learn JMETER from Scratch - (Performance + Load) Testing Tool");
    }
    @Test
    public void checkHideenElement(){
        WebElement element=find("//input[@id='displayed-text']");
        System.out.println(element.isDisplayed());
        type("//input[@id='displayed-text']","nabil");
        wait(3);
        click("//input[@id='hide-textbox']");
      //  type("//input[@id='name']","nabil10");
        System.out.println(element.isDisplayed());
        click("//input[@id='show-textbox']");
        System.out.println(element.isDisplayed());


    }

    @Test
    public void handletable2() {
        wait(5);
        WebElement element = find("//div[@class=\"tableFixHead\"]/table[@id=\"product\"]/tbody/tr[7]");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait(4);
        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("window.scrollBy(0,-100)");
        //System.out.println(element.getText());
        WebElement element2=find("//div[@class='totalAmount']");
        String text=element2.getText();
        if(Integer.parseInt(text.substring(text.lastIndexOf(" ")+1))==200){
            System.out.println("they ARE equals");
        }else{
            System.out.println("not equals");
        }

    }
    @Test
    public void hoverOver(){
        wait(3);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");
        wait(3);
       WebElement element= find("//button[@id='mousehover']");
       Actions actions=new Actions(driver);
       actions.moveToElement(element).build().perform();
       click("//a[@href='#top']");
        js.executeScript("window.scrollBy(0,1000)");
        actions.moveToElement(element).build().perform();
        click("//a[text()='Reload']");
    }
    @Test
    public void testIframe(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        wait(5);
        driver.switchTo().frame("iframe-name");
        WebElement element=find("//a[@class='theme-btn'][contains(text(),'JOIN')]");
        wait(5);
        js.executeScript("arguments[0].scrollIntoView();", element);
        wait(5);
        click("//a[@class='theme-btn'][contains(text(),'JOIN')]");

    }


}





