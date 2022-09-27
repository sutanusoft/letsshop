package testcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import cart.CartCatalogue;
import product.ProductCatalogue;
import utill.Base;
import utilltest.RetryFailedTC;

public class ErrorValidationsTest extends Base {

	@Test(groups="ErrorHandling", retryAnalyzer=RetryFailedTC.class)
	public void LoginErrorValidation() throws IOException{
		
		//Correct credentials->>("dasgupta.abhishekit@gmail.com", "Abhi@12345");
		landingpage.loginApplication("dasgupta.bishekit@gmail.com", "Abhi@12345");
		
		//relative css setector->
		//.ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		landingpage.getLoginErrorMessage();
		Assert.assertEquals("Incorrect email r password.", landingpage.getLoginErrorMessage()); //correct: "Incorrect email or password."
		
	}
	
	@Test(groups="ErrorHandling",retryAnalyzer=RetryFailedTC.class)
	public void ProductCatalogueErrorValidationTest() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		String productName="zara coat 3";
		
		//landingpage.loginApplication("dasgupta.abhishekit@gmail.com", "Abhi@12345");
		landingpage.loginApplication("dasgupta.abhishektech@gmail.com", "Abhi@12345");
		
		Thread.sleep(3000);
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		//List<WebElement> products=productcatalogue.getProductList();
		Thread.sleep(3000);
		productcatalogue.addProductToCart(productName);
		CartCatalogue cartCata=productcatalogue.goToCart(); 
		
		Boolean match=cartCata.verifyProductDisplay("zara coat 33");
		Assert.assertFalse(match);
		

} 
}