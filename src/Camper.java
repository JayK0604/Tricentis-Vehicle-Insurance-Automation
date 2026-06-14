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

public class Camper {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Website
		driver.get("https://sampleapp.tricentis.com/101/index.php");
		driver.findElement(By.linkText("Camper")).click();
		
		// 1] Enter Vehicle Details
		
		// make
		WebElement make = driver.findElement(By.id("make"));
		Select makeDropDown = new Select(make);
		makeDropDown.selectByVisibleText("BMW");
		
		// Enginer preformance
		driver.findElement(By.id("engineperformance")).sendKeys("150");
		
		//DOM
		driver.findElement(By.id("dateofmanufacture")).sendKeys("09/10/2021");
		
		//no. of seats
		WebElement seats = driver.findElement(By.id("numberofseats"));
		Select seatsDropDown = new Select(seats);
		seatsDropDown.selectByVisibleText("2");
		
		
		// =============== Fuel Type
		WebElement fuelType = driver.findElement(By.id("fuel"));
		Select typeDropDown = new Select(fuelType);
		typeDropDown.selectByVisibleText("Petrol");
		
		// Payload
		driver.findElement(By.id("payload")).sendKeys("180");
				
		// Total weight
		driver.findElement(By.id("totalweight")).sendKeys("1000");
				
		// List Price
		driver.findElement(By.id("listprice")).sendKeys("25000");
				
		// Annual Mileage
		driver.findElement(By.id("annualmileage")).sendKeys("12000");
				
		// next button
		driver.findElement(By.id("nextenterinsurantdata")).click();
		
		
		// 2] Enter Insurnace Data
		
		// First Name
		driver.findElement(By.id("firstname")).sendKeys("Rahul");
						
		// Last name
		driver.findElement(By.id("lastname")).sendKeys("Sharma");
						
		//DOB
		driver.findElement(By.id("birthdate")).sendKeys("11/20/1998");
						
		//Country
		WebElement country = driver.findElement(By.id("country"));
		Select countryDropDown = new Select(country);
		countryDropDown.selectByVisibleText("India");
						
		// Zip code
		driver.findElement(By.id("zipcode")).sendKeys("411011");
						
		//occupation
		WebElement occ = driver.findElement(By.id("occupation"));
		Select occDropDown = new Select(occ);
		occDropDown.selectByVisibleText("Selfemployed");
						
		// hobbies
		String hobbies[] = {
				"Bungee Jumping",
				"Skydiving",
				"Other"
		};
						
		for(String hobby : hobbies) { // here it looks like this ex (//label[contains(.,'Speeding')]) and so on
			driver.findElement(By.xpath("//label[contains(.,'" + hobby + "')]")).click();
		}
						
		// Next Button
		driver.findElement(By.id("nextenterproductdata")).click();

		// 3] Enter Product Data
		
		//Start date
		LocalDate futureDate = LocalDate.now().plusMonths(1).plusDays(1);
		
		DateTimeFormatter format= DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String startDate = futureDate.format(format);
		
		driver.findElement(By.id("startdate")).sendKeys(startDate);
				
		// insureance sum
		WebElement sum = driver.findElement(By.id("insurancesum"));
		Select sumDropDown = new Select(sum);
		sumDropDown.selectByVisibleText("5.000.000,00");
				
		// Damage insuarence
		WebElement damage = driver.findElement(By.id("damageinsurance"));
		Select damageDropDown = new Select(damage);
		damageDropDown.selectByVisibleText("Partial Coverage");
				
		// optional Products 
		// (.)-> it check all child and parent elements also 
		driver.findElement(By.xpath("//label[contains(.,'Euro Protection')]")).click();
		driver.findElement(By.xpath("//label[contains(.,'Legal Defense Insurance')]")).click();
				
		// Next Button
		driver.findElement(By.id("nextselectpriceoption")).click();
		
		// 4] Select Price option
		
		// Expected Data
		String expPrice = "166.00";
		String expClaim = "No";
		String expDiscount = "No";
		String expCover = "No";
				
		// Actual Data
		String actPrice = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr/td[2]/span")).getText();
		String actClaim = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr[2]/td[2]")).getText();
		String actDiscount = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr[3]/td[2]")).getText();
		String actCover = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr[4]/td[2]")).getText();
				
		if(expPrice.equals(actPrice) && expClaim.equals(actClaim) && expDiscount.equals(actDiscount) && expCover.equals(actCover)) {
					
		WebElement ultimate = driver.findElement(By.id("selectultimate"));

		// It used when hidden element ex(left: -9999px)
		JavascriptExecutor js = (JavascriptExecutor) driver;
					
		// yaha internally platinum.click() ho raha hai
		// platinum -> fist arg
		js.executeScript("arguments[0].click();", ultimate);
					
		} else {
			System.out.println("Something Went Wrong...");
		}
				
		Thread.sleep(3000);
		
		// Next button
		driver.findElement(By.id("nextsendquote")).click();	
		
		
		// 5] Send Quote
		
		// Email
		driver.findElement(By.id("email")).sendKeys("rahulsharma123@gmail.com");
		
		// Phone Number
		driver.findElement(By.id("phone")).sendKeys("7428956892");
		
		// Username
		driver.findElement(By.id("username")).sendKeys("Rahul");
		
		// password
		driver.findElement(By.id("password")).sendKeys("Rahul@12345");
		
		// confirm pass
		driver.findElement(By.id("confirmpassword")).sendKeys("Rahul@12345");
		
		// Send Button
		driver.findElement(By.id("sendemail")).click();
		
		Thread.sleep(10000);
		
		// take screenshot
		File f1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f1, new File("F:\\Seed\\Automation Testing\\AutomationProject\\Tricentis_Vehicle_Insurance_App\\ScreenShots\\Camper.jpeg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Press ok Button
		driver.findElement(By.className("confirm")).click();
		
		//Back to main page
		driver.findElement(By.id("backmain")).click();
		

	}

}
