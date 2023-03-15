package com.abhi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceCode {
	
	Scanner sc = new Scanner(System.in);
	int user_choice;
	
	public boolean choicecode() throws InputMismatchException
	{
		 boolean c = true;
	        System.out.println("Press 1 to continue press 2 to exit");
	        try
	        {
	        	 user_choice = sc.nextInt();
	        }
	        catch(InputMismatchException ex)
	        {
	        	System.out.println("Invalid input");
	        	sc.nextLine();
	        	choicecode();
	        }
	       
	        if(user_choice ==2)
	        {
	            c = false;
	        }
	        return c;
	}

}
