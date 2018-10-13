package com.abc.magento;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginValidation 
{
	static XSSFWorkbook book;
	static XSSFSheet sheet;
	static WebDriver driver;
	static String getCellValue(int rnum,int cnum)
	{
		XSSFRow row=sheet.getRow(rnum);
		XSSFCell cell=row.getCell(cnum);
		String data=cell.getStringCellValue();
		return data;
	}
	public static void main(String[] args) throws Exception 
	{
		FileInputStream fis=new FileInputStream("src\\com\\abc\\utilities\\hybrid.xlsx");
		book=new XSSFWorkbook(fis);
		sheet=book.getSheet("MagentoApplication");
		int numberOfRows=sheet.getPhysicalNumberOfRows();
		System.out.println(numberOfRows);
		for(int i=1;i<numberOfRows;i++)
		{
			String action=getCellValue(i,2);
			System.out.println(action);
			switch (action) {
			case "open":
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				break;
			case "navigate":
				driver.get(getCellValue(i,4));
				break;
			case "click":
				driver.findElement(By.xpath(getCellValue(i, 3))).click();
				break;
			case "type":
				driver.findElement(By.xpath(getCellValue(i, 3))).sendKeys(getCellValue(i, 4));
				break;
			case "quit":
				driver.quit();
				break;
			
			default:
				System.out.println("Invalid input");
				break;
			}
		}
		
		
		
		
		
	}
}
