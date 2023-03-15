package com.abhi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {

    public static boolean CONTINUE = true;
    public static Scanner input = new Scanner(System.in);
	
	public ExcelWrite() throws IOException
	{
		  while (CONTINUE)
	        {
	            InsertRecords();
	            System.out.println("Would you like to add more records? press 1 for yes and 2 for no");
	            int answer = input.nextInt();
	            
	            if(answer == 2)
	            {
	                CONTINUE = false;
	            }
	        }

		
	}
	
	public static  void InsertRecords() throws IOException {
        File practice_file = new File("D:\\Abhishek\\userDetails.xlsx");


        Map<Integer, UserDetails>user = new TreeMap<>();

        System.out.print("Enter your roll no: ");
        int rollno =  input.nextInt();
        System.out.print("Enter your firstname: ");
        String firstname = input.next();
        System.out.print("Enter your lastname:");
        String lastname = input.next();
        System.out.print("Enter your age: ");
        int age = input.nextInt();
        System.out.print("Enter Phoneno:");
        String phonenumber = input.next();
        System.out.print("Enter your email:");
        String email = input.next();

        user.put(rollno, new UserDetails(rollno, firstname, lastname, age, phonenumber, email));

        try
        {
            FileInputStream fis = new FileInputStream(practice_file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(user.get(rollno).rollno);
            row.createCell(1).setCellValue(user.get(rollno).firstname);
            row.createCell(2).setCellValue(user.get(rollno).lastname);
            row.createCell(3).setCellValue(user.get(rollno).age);
            row.createCell(4).setCellValue(user.get(rollno).PhoneNo);
            row.createCell(5).setCellValue(user.get(rollno).email);
            fis.close();
            System.out.println("Row number is :"+rowCount);

            FileOutputStream fos = new FileOutputStream(practice_file);
            workbook.write(fos);
            workbook.close();
            fos.close();
            System.out.println("File has been updated");
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }
	
	

}
