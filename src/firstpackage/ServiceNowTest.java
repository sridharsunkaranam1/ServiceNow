package firstpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ServiceNowTest extends BaseTest {
	
	
/*	public WebDriver driver;
	public String driverpath="";
	public Properties prop; */
	
	 
	
@Test(priority=0)	
  public void login() {	
	driver.findElement(By.linkText("login")).click();
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(prop.getProperty("user"));
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
	if(driver.findElement(By.id("rememberMe")).isSelected())
	{
	driver.findElement(By.id("rememberMe")).click();
	}
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	String actTitle=driver.getTitle();
	String expTitle="gurukula";
	Assert.assertTrue(actTitle.equalsIgnoreCase(expTitle));
  }
  
@Test(priority=1)	
public void addTest() {	
	
	driver.findElement(By.xpath("//span[@translate='global.menu.entities.main']")).click();
	driver.findElement(By.xpath("//a[@href='#/branch']")).click();
	
	driver.findElement(By.id("searchQuery")).sendKeys(prop.getProperty("BranchName"));
	driver.findElement(By.xpath("//button[@ng-click='search()']")).click();
	int rowSize1=driver.findElements(By.xpath("//table/tbody/tr")).size();
	System.out.println("Row size"+rowSize1);
	if(rowSize1==0)
	{
	driver.findElement(By.xpath("//button/span[2][@translate='gurukulaApp.branch.home.createLabel']")).click();
	driver.findElement(By.name("name")).sendKeys(prop.getProperty("BranchName"));
	driver.findElement(By.name("code")).sendKeys(prop.getProperty("BranchCode"));
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	driver.findElement(By.id("searchQuery")).clear();
	driver.findElement(By.id("searchQuery")).sendKeys(prop.getProperty("BranchName"));
	driver.findElement(By.xpath("//button[@ng-click='search()']")).click();
	int rowSize=driver.findElements(By.xpath("//table/tbody/tr")).size();
	System.out.println("Row size"+rowSize);
	Assert.assertEquals(rowSize, 1);
	}
}

@Test(priority=2)	
public void viewTest() {	
	driver.findElement(By.xpath("//button[./*[text()='View']]")).click();
	String act=driver.findElement(By.xpath("//td[./span[text()='Name']]/following-sibling::td/input")).getAttribute("value");

	Assert.assertEquals(act, prop.getProperty("BranchName"), "Actual is "+act);
	driver.findElement(By.xpath("//span[@translate='global.menu.entities.main']")).click();
	driver.findElement(By.xpath("//a[@href='#/branch']")).click();
}

@Test(priority=3)	
public void deleteTest() throws InterruptedException {	
	
	driver.findElement(By.id("searchQuery")).clear();
	driver.findElement(By.id("searchQuery")).sendKeys(prop.getProperty("BranchName"));
	driver.findElement(By.xpath("//button[@ng-click='search()']")).click();
	
	driver.findElement(By.xpath("//tr/td[4]/button[./*[text()='Delete']]")).click();
	driver.findElement(By.xpath("(.//button/span[text()='Delete'])[1]")).click();
	
	driver.findElement(By.id("searchQuery")).clear();
	driver.findElement(By.id("searchQuery")).sendKeys(prop.getProperty("BranchName"));
	driver.findElement(By.xpath("//button[@ng-click='search()']")).click();
	int rowSize=driver.findElements(By.xpath("//table/tbody/tr")).size();
	System.out.println("Row size"+rowSize);
	Assert.assertEquals(rowSize, 0);
	
}


}
