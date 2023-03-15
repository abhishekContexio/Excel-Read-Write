package com.abhi;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.abhi.ExcelRead;
import com.abhi.ExcelWrite;

public class Main {
	System.out.println("This is excel program");
	public static boolean CONTINUE = true;
	static ChoiceCode user_choice = new ChoiceCode();
	static int number;
	public static void main(String[] args) throws IOException, InputMismatchException
	{
        Scanner sc = new Scanner(System.in);
        while (CONTINUE)
        {
        	
        		System.out.println("Welcome to the excel program what would you like to do? \n 1.read \n 2.write ");
                try 
                {
                	number = sc.nextInt();
                }
                catch(InputMismatchException ex)
                {
                	System.out.println("not a valid input");
                	sc.nextLine();
                	continue;
                }
        		
        	
        	
            
            try
            {
                switch (number)
                {
                    case 1:
                    	
                        new ExcelRead();
                        CONTINUE =user_choice.choicecode();
                        break;
                    case 2:
                        new ExcelWrite();
                        CONTINUE = user_choice.choicecode();
                        break;
                    default:
                        System.out.println("Invalid entry please enter a valid entry");
                        CONTINUE = user_choice.choicecode();
                        break;


                }

            }
            catch (Exception e)
            {
                
                e.printStackTrace();
            }
            

        }

    }


}
