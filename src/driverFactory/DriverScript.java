package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String Inputpath="D:\\ECLIPSE WORKSPACE\\DDT_Framework\\TestInput\\Driverscriptlogindata.xlsx";
String Outputpath="D:\\ECLIPSE WORKSPACE\\DDT_Framework\\TestOutput\\DataDrivenResults.xlsx";
ExtentReports report;
ExtentTest test;
@Test
public void startTest( ) throws Throwable {
	
	//define path for html
	report = new ExtentReports("./Reports/DataDriven.html");
	
	//create object for excelfileutil class
	ExcelFileUtil xl= new ExcelFileUtil(Inputpath);
	
	//count no of rows in a sheet
	int rc=xl.rowCount("Login");
	
	//count no of cells in row
	int cc=xl.cellCount("Login");
	Reporter.log(rc+"   "+cc,true);
	for (int i = 1; i <=rc; i++) {
		test=report.startTest("validateLogin");
		String user=xl.getCellData("Login", i, 0);
		String pass=xl.getCellData("Login", i, 1);

		//call login method from functional library
		boolean res=FunctionLibrary.verifyLogin(user, pass);
		if (res) {
			
			//write as login success into result cell
			xl.setCellData("Login", i, 2, "LoginSuccess", Outputpath);
			
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "Pass", Outputpath);
			test.log(LogStatus.PASS, "Login Sucess");
			}
		else
		   {
          //take screenshot 
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./screens/iterations/"+"   "+i+"Loginpage.png"));
			 
			//write as login fail into result cell
			xl.setCellData("Login", i, 2, "LoginFail", Outputpath);
			
			//write as fail into status cell
			xl.setCellData("Login", i, 3, "Fail", Outputpath);
			test.log(LogStatus.FAIL, "Login unsuccess");
			
			}
			
		report.endTest(test);
		report.flush();
		
	                         	}	
	                }
	           }
