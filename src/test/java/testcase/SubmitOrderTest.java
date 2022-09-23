package testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cart.CartCatalogue;
import order.OrderConfirmation;
import order.OrderList;
import payment.PaymentThroughCard;
import product.ProductCatalogue;
import utill.Base;

public class SubmitOrderTest extends Base {
	
	@Test(dataProvider="getData", groups= {"PurchaseProducts"})
	public void SubmitOrderTest(HashMap<String,String> input) throws IOException{
		
		landingpage.loginApplication(input.get("email"), input.get("password"));
		
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		List<WebElement> products=productcatalogue.getProductList();
		
		productcatalogue.addProductToCart(input.get("productName"));
		
		CartCatalogue cartCata=productcatalogue.goToCart(); //Navigate to Cart module. And calling that method through child class object as child class object has properties of parent class.		
		
		//CartCatalogue cartCata=new CartCatalogue(driver);
		Boolean match=cartCata.verifyProductDisplay(input.get("productName"));
		
		Assert.assertTrue(match);   //Validation can't go to Page Object Files
		
		cartCata.checkOut();
		
		PaymentThroughCard payment = new PaymentThroughCard(driver);
		payment.selectCountry("India");
		payment.placeOrder();
		
		OrderConfirmation confirm=new OrderConfirmation(driver);
		String orderStatus=confirm.confirmationStatus();
		Assert.assertTrue(orderStatus.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}
	
	@Test (dependsOnMethods={"SubmitOrderTest"},groups= {"PurchaseProducts"})
	public void OrderHistoryTest() throws InterruptedException
	{
		String productName="zara coat 3";
		//To verify Zara Coat 3 is displaying in Order page
		landingpage.loginApplication("dasgupta.abhishek0869@gmail.com", "Abhi@12345");
		OrderList orders=new OrderList(driver);
		orders.goToOrdersPage();
		Assert.assertTrue(orders.verifyOrderDetails(productName));
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "dasgupta.abhishek0869@gmail.com");
		map1.put("password", "Abhi@12345");
		map1.put("productName", "zara coat 3");
		
		HashMap<String,String> map2=new HashMap<String,String>();
		map2.put("email", "abhi@gmail.com");
		map2.put("password", "Abhi@12345");
		map2.put("productName", "adidas original");
		return new Object[][] {{map1} , {map2}};
	}

}
