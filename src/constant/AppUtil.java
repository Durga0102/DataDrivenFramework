package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver;
public static Properties config;
@BeforeTest
public static void Setup( )throws Throwable
          {
config=new Properties();
config.load(new FileInputStream("D:\\ECLIPSE WORKSPACE\\DDT_Framework\\PropertiesFile\\Environment.property"));
	driver= new ChromeDriver();
       }
@AfterTest
public static void tearDown( )
        {
	driver.close();
	 }
  }


