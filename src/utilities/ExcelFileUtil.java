package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//construct for reading excel path
	public ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fi= new FileInputStream(Excelpath);
		wb= new XSSFWorkbook(fi);
	}
	
	//method for row count 
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	
	// method to count no of cells ina row
	public int cellCount(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	
	// method for cell data
	public String getCellData(String sheetName,int row,int column)
	{
		String data=" ";
		if (wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
		int celldata=(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
	//method for set celldata
	public void setCellData(String sheetName,int row,int column,String status,String writeexcel) throws Throwable
	{
		
		//get sheet from wb
		XSSFSheet ws=wb.getSheet(sheetName);
		
		//get roe from sheet
		XSSFRow rowNum=ws.getRow(row);
		
		//create cell ina row
		XSSFCell cell=rowNum.getCell(column);
		
		//write status
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style= wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle  style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle  style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE_GREY.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
			FileOutputStream fo=new FileOutputStream(writeexcel);
			wb.write(fo);
	}
	public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl= new ExcelFileUtil("d:/Logindata.xlsx");
		
		//count no of rows
		int rc=xl.rowCount("Login");
		
		//count no of cells ina row
		int cc=xl.cellCount("Login");
		System.out.println(rc+"     "+cc);
		for (int i = 1; i <= rc; i++)
		{
		String user=xl.getCellData("Login", i, 0);
		String pass=xl.getCellData("Login", i, 1);
		System.out.println(user+"   "+pass);
		
		//xl.setCellData("Login", i, 2, "Pass","d:/Results.xlsx");
		xl.setCellData("Login", i, 2, "Fail","d:/Results.xlsx");
		//xl.setCellData("Login", i, 2, "Blocked","d:/Results.xlsx");

		      }
	     }
    }
		

