package authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import service.AbstractComponent;

public class LogIn extends AbstractComponent {

	WebDriver driver;
	public LogIn(WebDriver driver)
	{
		super(driver); //Sending life of driver from child to it's parent class(AbstractComponent)
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"))
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//driver.findElement(By.id("login"))
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessageLogin;
	
	//Login Application method
	public void loginApplication(String email, String password)  //if returns, then return type will be ProductCatalogue
	{
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		//ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		//return productcatalogue;
		
	}
	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getLoginErrorMessage()
	{
		waitForWebElementToAppear(errorMessageLogin);
		return errorMessageLogin.getText();
	}
	
	
	
	
	
	
}
