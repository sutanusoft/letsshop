package cart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import service.AbstractComponent;

public class CartCatalogue extends AbstractComponent{

	WebDriver driver;
	public CartCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	By cButton=By.cssSelector(".totalRow button");

	/*List <WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match= cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)); */
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	//By.cssSelector(".totalRow button"
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	public void checkOut()
	{
		waitForElementToAppear(cButton);
		checkOut.click();
		//new CheckOutPage(driver);
	}
	
	
	
	
	
}
