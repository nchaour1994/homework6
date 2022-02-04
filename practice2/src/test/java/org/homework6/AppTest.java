package org.homework6;


import base.commonApi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class AppTest extends commonApi
{
    @Test
    public void testRadioButton() {
        click("//fieldset/*[(text()='Radio Button Example')]/following::label/input[@name='radioButton']");
        List<WebElement> list = driver.findElements(By.xpath("//fieldset/*[(text()='Radio Button Example')]/following::label/input[@name='radioButton']"));

        for (WebElement element1 : list) {
            element1.click();
            wait(5);
        }


    }
}
