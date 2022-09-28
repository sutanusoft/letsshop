package testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import cart.CartCatalogue;
import product.ProductCatalogue;
import util.Base;
import utiltest.RetryFailedTC;

public class ErrorValidationsTest extends Base {

	@Test(groups="ErrorHandling", retryAnalyzer=RetryFailedTC.class)
	public void LoginErrorValidation() throws IOException{
		
		landingpage.loginApplication("dasgupta.bishekit@gmail.com", "Abhi@12345");
		
		landingpage.getLoginErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingpage.getLoginErrorMessage()); //correct: "Incorrect email or password."
		
	}
	
	@Test(groups="ErrorHandling",retryAnalyzer=RetryFailedTC.class)
	public void ProductCatalogueErrorValidationTest() throws IOException, InterruptedException{
		String productName="zara coat 3";
		
		landingpage.loginApplication("dasgupta.abhishektech@gmail.com", "Abhi@12345");
		
		Thread.sleep(3000);
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);

		Thread.sleep(3000);
		productcatalogue.addProductToCart(productName);
		CartCatalogue cartCata=productcatalogue.goToCart(); 
		
		Boolean match=cartCata.verifyProductDisplay("zara coat 33");
		Assert.assertFalse(match);
		

} 
}