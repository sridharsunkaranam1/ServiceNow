package firstpackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {
	public static WebDriver driver;
	public static String driverpath="";
	public static Properties prop;
	
	public static String baseURL="";
	public static String path = System.getProperty("user.dir");
	public static String otherFolder = path + "\\src\\firstpackage\\config.properties";
	@BeforeTest
	  public void before() throws IOException {
		
		prop=new Properties();
		System.out.println(otherFolder);
		FileInputStream fis=new FileInputStream(otherFolder);
		prop.load(fis);
		System.out.println(prop.getProperty("geckodriverpath"));
		driverpath=prop.getProperty("geckodriverpath");
		
		System.setProperty("webdriver.gecko.driver", driverpath);
		System.out.println("launching FF");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		baseURL=prop.getProperty("EnvbaseURL");
		System.out.println(baseURL);
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	  }  
	


  @AfterTest
  public void after() {
	  driver.quit();
  }
}
