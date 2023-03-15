package com.abhi;

public class UserDetails {
	   String firstname;
	    int age;
	    String lastname;
	    String PhoneNo;
	    int rollno;
	    String email;


	    UserDetails(int rollno, String firstname, String lastname, int age, String phoneNo,  String email  )
	    {
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.PhoneNo = phoneNo;
	        this.age = age;
	        this.rollno = rollno;
	        this.email = email;

	    }

	    public String toString()
	    {
	        return firstname +" "+ age;
	    }

}


