package payment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import service.AbstractComponent;

public class PaymentThroughCard extends AbstractComponent{

	/*Actions act=new Actions(driver);
	act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	driver.findElement(By.cssSelector(".action__submit")).click(); */
	
	WebDriver driver;
	public PaymentThroughCard(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryLocator;
	
	By waitForIndia= By.cssSelector(".ta-results");
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectIndia;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
	public void selectCountry(String country)
	{
		Actions act=new Actions(driver);
		act.sendKeys(countryLocator,country).build().perform();
		waitForElementToAppear(waitForIndia);
		
		selectIndia.click();
		
	}
	
	public void placeOrder()
	{
		
		submit.click();
		
	}
	
	
	
	
}
