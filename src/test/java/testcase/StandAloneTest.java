package testcase;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import authentication.LogIn;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName="zara coat 3";
		
		WebDriverManager.chromedriver().setup();  //Automatically download Chrome Driver & for that have to download WebDriver Manager from Maven Repo
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LogIn landingpage=new LogIn(driver);
		
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("dasgupta.abhishek0869@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abhi@12345");
		driver.findElement(By.id("login")).click();
		System.out.println(driver.getTitle());
		
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("zara coat 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Select exMonth=new Select(driver.findElement(By.xpath("//body//app-root//select[1]")));
		exMonth.selectByVisibleText("07");
		Select exDate=new Select(driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[1]/form/div/div[2]/div[1]/select[2]")));
		exDate.selectByVisibleText("19");
		
		Actions act=new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String orderStatus=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println("Status of Order--->>> "+orderStatus);
		Assert.assertEquals(orderStatus, 	"THANKYOU FOR THE ORDER.");
		driver.close();
	}

}
