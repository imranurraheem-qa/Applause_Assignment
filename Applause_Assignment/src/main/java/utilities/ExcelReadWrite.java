package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReadWrite 
{
	public static String readExcelData(String sh, int row, int cell) throws Exception
	{
		String path = System.getProperty("user.dir") +"/src/test/resources/data/Applause_Products.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
//		XSSFSheet sheet = wb.getSheetAt(sh);
		XSSFSheet sheet = wb.getSheet(sh);
		String data = null;
		try {
			XSSFRow rw = sheet.getRow(row);
			XSSFCell cl = rw.getCell(cell);
			CellType cellType = cl.getCellType();
			
			switch(cellType)
			{
				case STRING:
	                data = cl.getStringCellValue(); 
	                break;
				case NUMERIC:
					int converter = (int) cl.getNumericCellValue();
					data = String.valueOf(converter);
					break;
				default:
					break;
			}
			
			TestUtils.log().info("Excel data: "+data);
		}catch(Exception e)
		{
			TestUtils.log().error("Failed to read excel data");
		}
		
		fis.close();
		wb.close();
		
		return data;
	}
	
	public static void writeExcelData(String sh, int row, int cell, String value) throws Exception
	{
		String path = System.getProperty("user.dir") +"/src/test/resources/data/Applause_Products.xlsx";
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
//		XSSFSheet sheet = wb.getSheetAt(sh);
		XSSFSheet sheet = wb.getSheet(sh);
		
		XSSFRow rw = sheet.getRow(row);
		if(rw == null)
		{
			rw = sheet.createRow(row);
		}
		
		XSSFCell cl = rw.getCell(cell);
		if(cl == null)
		{
			cl = rw.createCell(cell); 
		}
		
		cl.setCellValue(value);
		
		fis.close();		
		
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();
	}

}
