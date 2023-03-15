package com.abhi;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelRead {
	
	public ExcelRead()
	{
		try
		{
			File file = new File("D:\\Abhishek\\userDetails.xlsx");
			FileInputStream fis = new FileInputStream(file);
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
	            XSSFSheet sheet = wb.getSheetAt(0);
	            Iterator<Row> itr = sheet.iterator();
	            while (itr.hasNext()) {
	                Row row = itr.next();
	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
	                    switch (cell.getCellType()) 
	                    {
	                        case NUMERIC:
	                            long number = (long) cell.getNumericCellValue();
	                            System.out.print(number + "\t\t\t");
	                            break;

	                        case STRING:
	                            System.out.print(cell.getStringCellValue() + "\t\t\t");
	                            break;


	                        default:
	                    }
	                }

	                System.out.println("");


	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
