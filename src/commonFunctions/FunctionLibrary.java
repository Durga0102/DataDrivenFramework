package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean verifyLogin(String username,String password)throws Throwable
         {
	driver.get(config.getProperty("Url"));
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.findElement(By.cssSelector(config.getProperty("Objuser"))).sendKeys(username);
	driver.findElement(By.cssSelector(config.getProperty("Objpass"))).sendKeys(password);
	driver.findElement(By.cssSelector(config.getProperty("Objloginbtn"))).sendKeys(Keys.ENTER);
	String expected="dashboard";
	String actual=driver.getCurrentUrl();
	if (actual.contains(expected)) 
	    {
		Reporter.log("login Success"+expected+"      "+actual,true);
		return true;
	    }
	else
	{
		
		// capture error messgae
		Thread.sleep(3000);
		String errormessage=driver.findElement(By.cssSelector(config.getProperty("Objerrormessage"))).getText();
		Reporter.log(errormessage+"    "+expected+"     "+actual,true);
		return false;
		
		
		
	               }
	
               }
      }
