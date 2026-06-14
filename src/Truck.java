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

public class Truck {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Website
		driver.get("https://sampleapp.tricentis.com/101/index.php");
		driver.findElement(By.linkText("Truck")).click();
		
		// 1] Enter Vehicle Data
		
		// make
		WebElement make = driver.findElement(By.id("make"));
		Select makeDropDown = new Select(make);
		makeDropDown.selectByVisibleText("Honda");
		
		// Engine Performance
		driver.findElement(By.id("engineperformance")).sendKeys("120");
		
		// DOM
		driver.findElement(By.id("dateofmanufacture")).sendKeys("08/15/2000");
		
		//No. of seats
		WebElement seats = driver.findElement(By.id("numberofseats"));
		Select seatsDropDown = new Select(seats);
		seatsDropDown.selectByVisibleText("2");
		
		// Fuel type
		WebElement fuel = driver.findElement(By.id("fuel"));
		Select fuelDropDown = new Select(fuel);
		fuelDropDown.selectByVisibleText("Petrol");
		
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
		
		
		// 2] Enter Insurance Data
		
		// First Name
		driver.findElement(By.id("firstname")).sendKeys("Jay");
		
		// Last name
		driver.findElement(By.id("lastname")).sendKeys("Prajapati");
		
		//DOB
		driver.findElement(By.id("birthdate")).sendKeys("08/15/2000");
		
		//Country
		WebElement country = driver.findElement(By.id("country"));
		Select countryDropDown = new Select(country);
		countryDropDown.selectByVisibleText("India");
		
		// Zip code
		driver.findElement(By.id("zipcode")).sendKeys("411052");
		
		//occupation
		WebElement occ = driver.findElement(By.id("occupation"));
		Select occDropDown = new Select(occ);
		occDropDown.selectByVisibleText("Unemployed");
		
		// hobbies
		String hobbies[] = {
				"Speeding",
				"Bungee Jumping",
				"Cliff Diving"
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
		sumDropDown.selectByVisibleText("3.000.000,00");
		
		// Damage insuarence
		WebElement damage = driver.findElement(By.id("damageinsurance"));
		Select damageDropDown = new Select(damage);
		damageDropDown.selectByVisibleText("Full Coverage");
		
		// optional Products 
		// (.)-> it check all child and parent elements also 
		driver.findElement(By.xpath("//label[contains(.,'Legal Defense Insurance')]")).click();
		
		// Next Button
		driver.findElement(By.id("nextselectpriceoption")).click();
		
		
		// 4] Select Price option
		
		// Expected Data
		String expPrice = "1,181.00";
		String expClaim = "Submit";
		String expDiscount = "5";
		String expCover = "Limited";
		
		// Actual Data
		String actPrice = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr/td[4]/span")).getText();
		String actClaim = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr[2]/td[4]")).getText();
		String actDiscount = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr[3]/td[4]")).getText();
		String actCover = driver.findElement(By.xpath("//table[@id='priceTable']/tbody/tr[4]/td[4]")).getText();
		
		if(expPrice.equals(actPrice) && expClaim.equals(actClaim) && expDiscount.equals(actDiscount) && expCover.equals(actCover)) {
			
			WebElement platinum =
			        driver.findElement(By.id("selectplatinum"));

			// It used when hidden element ex(left: -9999px)
			JavascriptExecutor js =
			        (JavascriptExecutor) driver;
			
			// yaha internally platinum.click() ho raha hai
			// platinum -> fist arg
			js.executeScript("arguments[0].click();",
			        platinum);
			
		} else {
			System.out.println("Something Went Wrong...");
		}
		
		Thread.sleep(3000);
		// Next button
		driver.findElement(By.id("nextsendquote")).click();	
		
		
		// 5] Send Quote
		
				// Email
				driver.findElement(By.id("email")).sendKeys("jayprajapati@gmail.com");
				
				// Phone Number
				driver.findElement(By.id("phone")).sendKeys("7248968523");
				
				// Username
				driver.findElement(By.id("username")).sendKeys("Jayp");
				
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
					FileUtils.copyFile(f1, new File("F:\\Seed\\Automation Testing\\AutomationProject\\Tricentis_Vehicle_Insurance_App\\ScreenShots\\Truck.jpeg"));
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				// Press ok Button
				driver.findElement(By.className("confirm")).click();
				
				//Back to main page
				driver.findElement(By.id("backmain")).click();

	}

}
