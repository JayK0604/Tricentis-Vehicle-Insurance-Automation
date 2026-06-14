import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;

public class AI_Automobile_Automation {
	public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open Website
        driver.get("https://sampleapp.tricentis.com/101/index.php");

        // Click Automobile Tab
        driver.findElement(By.id("nav_automobile")).click();

        // ================= VEHICLE DATA =================

        Select make = new Select(driver.findElement(By.id("make")));
        make.selectByVisibleText("Audi");

        driver.findElement(By.id("engineperformance")).sendKeys("120");

        driver.findElement(By.id("dateofmanufacture")).sendKeys("08/15/2024");

        Select seats = new Select(driver.findElement(By.id("numberofseats")));
        seats.selectByVisibleText("5");

        Select fuel = new Select(driver.findElement(By.id("fuel")));
        fuel.selectByVisibleText("Petrol");

        driver.findElement(By.id("listprice")).sendKeys("45000");

        driver.findElement(By.id("licenseplatenumber")).sendKeys("MH12AB1234");

        driver.findElement(By.id("annualmileage")).sendKeys("15000");

        driver.findElement(By.id("nextenterinsurantdata")).click();

        // ================= INSURANT DATA =================

        driver.findElement(By.id("firstname")).sendKeys("Jay");

        driver.findElement(By.id("lastname")).sendKeys("Prajapati");

        driver.findElement(By.id("birthdate")).sendKeys("05/15/1998");

        driver.findElement(By.xpath("//label[text()='Male']")).click();

        driver.findElement(By.id("streetaddress")).sendKeys("Nagpur");

        Select country = new Select(driver.findElement(By.id("country")));
        country.selectByVisibleText("India");

        driver.findElement(By.id("zipcode")).sendKeys("440001");

        driver.findElement(By.id("city")).sendKeys("Nagpur");

        Select occupation = new Select(driver.findElement(By.id("occupation")));
        occupation.selectByVisibleText("Employee");

        driver.findElement(By.xpath("//label[text()=' Speeding']")).click();

        driver.findElement(By.id("website")).sendKeys("https://google.com");

        driver.findElement(By.id("nextenterproductdata")).click();

        // ================= PRODUCT DATA =================

        // Future date mandatory
        driver.findElement(By.id("startdate")).sendKeys("08/25/2026");

        Select insuranceSum = new Select(driver.findElement(By.id("insurancesum")));
        insuranceSum.selectByVisibleText("3.000.000,00");

        Select meritRating = new Select(driver.findElement(By.id("meritrating")));
        meritRating.selectByVisibleText("Bonus 1");

        Select damageInsurance = new Select(driver.findElement(By.id("damageinsurance")));
        damageInsurance.selectByVisibleText("Full Coverage");

        driver.findElement(By.xpath("//label[text()='Euro Protection']")).click();

        Select courtesyCar = new Select(driver.findElement(By.id("courtesycar")));
        courtesyCar.selectByVisibleText("Yes");

        driver.findElement(By.id("nextselectpriceoption")).click();

        // ================= PRICE OPTION =================

        // Click Silver Radio Button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement silverPlan = wait.until(
        ExpectedConditions.elementToBeClickable(
        By.xpath("(//label[@class='choosePrice ideal-radiocheck-label'])[1]")));

        silverPlan.click();

        Thread.sleep(2000);

        driver.findElement(By.id("nextsendquote")).click();

        // ================= SEND QUOTE =================

        driver.findElement(By.id("email")).sendKeys("test123@gmail.com");

        driver.findElement(By.id("phone")).sendKeys("9876543210");

        driver.findElement(By.id("username")).sendKeys("jaytest");

        driver.findElement(By.id("password")).sendKeys("Test@123");

        driver.findElement(By.id("confirmpassword")).sendKeys("Test@123");

        driver.findElement(By.id("Comments")).sendKeys("Automation Testing using Selenium");

        driver.findElement(By.id("sendemail")).click();

        // ================= SUCCESS MESSAGE =================

        Thread.sleep(10000);

        String successMsg = driver.findElement(
                By.xpath("//h2[contains(text(),'Sending e-mail success!')]"))
                .getText();

        System.out.println("Success Message: " + successMsg);

        // ================= SCREENSHOT =================

        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        File dest = new File("AutomationSuccess.png");

        FileUtils.copyFile(src, dest);

        System.out.println("Screenshot Saved Successfully");

        // Click OK Button
        driver.findElement(By.xpath("//button[text()='OK']")).click();

        // ================= RETURN TO MAIN PAGE =================

        driver.findElement(By.xpath("//a[@id='backmain']")).click();


        System.out.println("Returned to Main Page Successfully");

        driver.quit();
    }
}
