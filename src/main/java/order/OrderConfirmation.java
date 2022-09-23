package order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import service.AbstractComponent;

public class OrderConfirmation extends AbstractComponent {

	WebDriver driver;
	public OrderConfirmation(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	/*
	String orderStatus=driver.findElement(By.cssSelector(".hero-primary")).getText();
	System.out.println("Status of Order--->>> "+orderStatus);
	Assert.assertEquals(orderStatus, 	"THANKYOU FOR THE ORDER.");
	driver.close();
	*/

	@FindBy(css=".hero-primary")
	WebElement confirmationText;
	
	public String confirmationStatus()
	{
		String orderStatus=confirmationText.getText();
		System.out.println("Status of Order--->>> "+orderStatus);
		return orderStatus;
	}
	
}
