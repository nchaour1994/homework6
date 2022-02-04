package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class commonApi {

     public WebDriver driver=null;


    @BeforeMethod
    public void init(){
       String path=System.getProperty("user.dir");
      System.out.println("my path is "+path);
        System.setProperty("webdriver.chrome.driver","C:\\Users\\nchao\\IdeaProjects\\framework-homework6\\generic\\drivers\\chromedriver.exe");
        driver= new ChromeDriver();
      //  driver.get("https://www.walgreens.com/");
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        String current = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        String Expected="Practice Page";
        Assert.assertEquals(current,Expected);

    }

    public void click(String locator){
        try{
            driver.findElement(By.xpath(locator)).click();
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).click();
        }
    }

    public void type(String locator,String text ){
        try {
            driver.findElement(By.xpath(locator)).sendKeys(text);
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).sendKeys(text);
        }
    }
    public void listDropdown(String dropDownLocator, String nameOfAttribute){
        List<WebElement> dropDown=driver.findElements(By.xpath(dropDownLocator));
        for (WebElement element:dropDown) {
            System.out.println(element.getAttribute(nameOfAttribute));
        }

    }
    public void wait(int seconds ){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void selectFromDropDown(String dropdownLocator,String option){
        try {
            WebElement selectDrop = driver.findElement(By.xpath(dropdownLocator));
            Select select = new Select(selectDrop);
            try {
                select.selectByVisibleText(option);
            }catch(Exception e){
                select.selectByValue(option);
            }

        }catch (Exception e ){
            WebElement selectDrop = driver.findElement(By.cssSelector(dropdownLocator));
            Select select = new Select(selectDrop);
            try {
                select.selectByVisibleText(option);
            }catch(Exception e1){
                select.selectByValue(option);
            }
        }
    }
    public List<WebElement> getDropDownOptions(String dropdownLocator){
        WebElement selectDrop= null;
        try {
             selectDrop = driver.findElement(By.xpath(dropdownLocator));

        }catch(Exception e){
            selectDrop = driver.findElement(By.cssSelector(dropdownLocator));
        }
        Select select = new Select(selectDrop);
        List<WebElement> list =select.getOptions();
        return list ;
    }
    public void typeAndEnter(String locator ,String itemToSerch){
        try {
            driver.findElement(By.xpath(locator)).sendKeys(itemToSerch, Keys.ENTER);
        }catch(Exception e){
            driver.findElement(By.cssSelector(locator)).sendKeys(itemToSerch, Keys.ENTER);
        }
    }
    public void clear(String locator ){
        try {
            driver.findElement(By.xpath(locator)).clear();
        }catch (Exception e){
            driver.findElement(By.cssSelector(locator)).clear();
        }
    }
    public WebElement find(String locator ){
        WebElement element;
        try{
            element= driver.findElement(By.xpath(locator));
        }catch (Exception e){
            element= driver.findElement(By.cssSelector(locator));
        }
       return element ;
    }
    public void acceptAlert(String locator){
        wait(2);
        click(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }
    public void dismissAlert(String locator ){
        wait(2);
        click(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alt = driver.switchTo().alert();
        alt.dismiss();
    }
    public void searchInTable(String tableLocator, String itemToSearch ){
        WebElement table = find(tableLocator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (int j = 0; j < cols.size(); j++) {
                if (cols.get(j).getText().equals(itemToSearch)) {
                    System.out.println(cols.get(j).getText());
                }
            }

        }
    }


}
