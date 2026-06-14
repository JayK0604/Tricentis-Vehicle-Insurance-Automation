import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Automobile {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Website
		driver.get("https://sampleapp.tricentis.com/101/index.php");
		driver.findElement(By.linkText("Automobile")).click();
		
		// 1] Enter Vehicle Data

		// ==============Make=============
		WebElement make = driver.findElement(By.id("make"));
		Select makeDropdown = new Select(make);
		makeDropdown.selectByVisibleText("Audi");
		
		// ============= Engine performance=================
		driver.findElement(By.id("engineperformance")).sendKeys("120");
		
		// =============== Date of manufacture
		driver.findElement(By.id("dateofmanufacture")).sendKeys("05/15/2022");
		
		// =============== Number of Seats
		WebElement seats = driver.findElement(By.id("numberofseats"));
		Select seatsDropDown = new Select(seats);
		seatsDropDown.selectByVisibleText("5");
		
		// =============== Fuel Type
		WebElement fuelType = driver.findElement(By.id("fuel"));
		Select typeDropDown = new Select(fuelType);
		typeDropDown.selectByVisibleText("Petrol");
		
		// =============== List price
		driver.findElement(By.id("listprice")).sendKeys("35000");
		
		// =============== License Plate number
		driver.findElement(By.id("licenseplatenumber")).sendKeys("MH12AB1234");
		
		// =============== Annual Mileage
		driver.findElement(By.id("annualmileage")).sendKeys("12000");
		
		// Press next button
		driver.findElement(By.id("nextenterinsurantdata")).click();
		
		
		// 2] Enter Insurance Data
		
		// FirstName
		driver.findElement(By.id("firstname")).sendKeys("Jay");
		
		// LastName
		driver.findElement(By.id("lastname")).sendKeys("Prajapati");
		
		// DOB
		driver.findElement(By.id("birthdate")).sendKeys("08/15/2000");
		
		// Gender
		//driver.findElement(By.xpath("//input[@id = 'gendermale']")).click();
		//OR
		// driver.findElement(By.xpath("//span[text()='Male']")).click();
		//OR
		driver.findElement(By.xpath("//label[text()='Male']")).click();
		
		// Street Address
		driver.findElement(By.id("streetaddress")).sendKeys("MG Road");
		
		// Country
		WebElement country = driver.findElement(By.id("country"));
		Select countryDropDown = new Select(country);
		countryDropDown.selectByVisibleText("India");
		
		// Zip code
		driver.findElement(By.id("zipcode")).sendKeys("440001");
		
		// City
		driver.findElement(By.id("city")).sendKeys("Nagpur");
		
		// Occupation
		WebElement occupation = driver.findElement(By.id("occupation"));
		Select occupationDropDown = new Select(occupation);
		occupationDropDown.selectByVisibleText("Employee");
		
		
		// Hobbies for Multiple/Single Selection
		String hobbies[] = {
				"skydiving"
		};
		
		for(String hobby : hobbies) {
			driver.findElement(By.xpath("//input[@id='" + hobby + "']/parent::label")).click();
		}
		
		// Press next button
		driver.findElement(By.id("nextenterproductdata")).click();
		
		
		// 3] Enter Product Data
		
		//Start date
		LocalDate futureDate = LocalDate.now().plusMonths(1).plusDays(1);
		
		DateTimeFormatter format= DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String startDate = futureDate.format(format);
		
		driver.findElement(By.id("startdate")).sendKeys(startDate);
		
		// Insurance Sum
		WebElement sum = driver.findElement(By.id("insurancesum"));
		Select sumDropDown = new Select(sum);
		sumDropDown.selectByVisibleText("10.000.000,00");
		
		// Merit Rating
		WebElement rating = driver.findElement(By.id("meritrating"));
		Select ratingDropDown = new Select(rating);
		ratingDropDown.selectByVisibleText("Bonus 3");
		
		// Damage Insurance
		WebElement damage = driver.findElement(By.id("damageinsurance"));
		Select damageDropDown = new Select(damage);
		damageDropDown.selectByVisibleText("Full Coverage");
		
		// Optional Products
		driver.findElement(By.xpath("//input[@id='EuroProtection']/parent::label")).click();
		
		// Courtesy Car
		WebElement courtesy = driver.findElement(By.id("courtesycar"));
		Select courtesyDropDown = new Select(courtesy);
		courtesyDropDown.selectByVisibleText("Yes");
		
		// Press next button
		driver.findElement(By.id("nextselectpriceoption")).click();
		
		// 4] Select Price Option
		
		// expected values
		String expectedPrice = "344.00";
		String expectedClaim = "Submit";
		String expectedDiscount = "2";
		String expectedCover = "Limited";
		
		// Actual values
		String actualPrice = driver.findElement(By.xpath("//section[@id='pricePlans']/div/table/tbody/tr/td[3]/span")).getText();
		String actualClaim = driver.findElement(By.xpath("//section[@id='pricePlans']/div/table/tbody/tr[2]/td[3]")).getText();
		String actualDiscount = driver.findElement(By.xpath("//section[@id='pricePlans']/div/table/tbody/tr[3]/td[3]")).getText();
		String actualCover = driver.findElement(By.xpath("//section[@id='pricePlans']/div/table/tbody/tr[4]/td[3]")).getText();
		
		// Comparison
		
		if(expectedPrice.equals(actualPrice) && expectedClaim.equals(actualClaim) && expectedDiscount.equals(actualDiscount) && expectedCover.equals(actualCover)) {
			
			WebElement selection = driver.findElement(By.id("selectgold"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", selection);
			
		} else {
			System.out.println("Something went wrong...");
		}
		
		Thread.sleep(3000);
		// Next Button	
		driver.findElement(By.id("nextsendquote")).click();
		
		
		// 5] Send Quote
		
		// Email
		driver.findElement(By.id("email")).sendKeys("jayprajapati@test.com");
		
		// Username
		driver.findElement(By.id("username")).sendKeys("jayprajapati");
		
		// password
		driver.findElement(By.id("password")).sendKeys("Jay@12345");
		
		// confirm pass
		driver.findElement(By.id("confirmpassword")).sendKeys("Jay@12345");
		
		// Send Button
		driver.findElement(By.id("sendemail")).click();
		
		Thread.sleep(10000);
		
		// take screenshot
		File f1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f1, new File("F:\\Seed\\Automation Testing\\AutomationProject\\Tricentis_Vehicle_Insurance_App\\ScreenShots\\AutoMobile.jpeg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Press ok Button
		driver.findElement(By.className("confirm")).click();
		
		//Back to main page
		driver.findElement(By.id("backmain")).click();
		
		
		

	}

}
