package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import authentication.LogIn;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	public LogIn landingpage ;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fileInputStream);
		//Ternary operator to check browser from terminal(maven property) if there not then proceed from here Global property
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
		WebDriverManager.chromedriver().setup();  //Automatically download Chrome Driver & for that have to download WebDriver Manager from Maven Repo
		driver=new ChromeDriver();
		ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingPort(0).build();
		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//Code for firefox browser
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\driver\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//Code for IE browser
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\driver\\latestedgedriver.exe");
			driver=new EdgeDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod (alwaysRun=true)
	public LogIn launchApplication() throws IOException
	{
		WebDriver driver=initializeDriver();
		landingpage=new LogIn(driver);
		landingpage.goTo();
		
		return landingpage;
			
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	//Take ScreenShot for Failed Test Cases
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+ "//reports"+ testCaseName+ ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+ "//reports"+ testCaseName+ ".png";
	}
	
	
}


//For parallel execution use it into Suits-->>  parallel="tests"