/**
* Class: Software Implementation
* Instructor: Maria Boyle
* Description: Class Person - Developed for the Hotel System
* Date: dd/mm/yyyy
* @author Albert Clarke
* @version 1.0
*/
package ie.atu.hotel;

import java.io.*;
import java.util.Scanner;

// Person is an abstract class.  This means we use it ONLY to inherit from!
// We cannot create an object from class Person!
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Name name;	// COMPOSITION - Person HAS-A name
	protected String phoneNumber;

	// Default Constructor
	// Will be called by subclasses 
	public Person(){
		name=new Name();
		phoneNumber="";
	}
	
	// Overloaded Initialization Constructor
	public Person(Name name, String phoneNumber) {
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	
	// toString() method
	@Override  // Overrides Object toString()
	public String toString() {
		return name + "\t" + phoneNumber;		
	}
	
	// equals() method
	@Override  // Overrides Object equals()
	public boolean equals(Object obj) {
		Person pObj;
		if(obj instanceof Person)
			pObj=(Person)obj;
		else
			return false;
		
		return name.equals(pObj.name)&&
			   phoneNumber.equals(pObj.phoneNumber);		
	}

	
	// get() methods
	public Name getName() {
		return this.name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	// set() methods
	public void setName(Name name) {
		this.name=name;
	}
	public void setPhoneNumber(String phoneNumberIn) {
		phoneNumber=phoneNumberIn;
	}

	// read() method
	public boolean read() {
	    Scanner kb = new Scanner(System.in);
	    
	    try {
	        System.out.print("Title: ");
	        this.name.setTitle(kb.nextLine().trim());
	        
	        System.out.print("First Name: ");
	        this.name.setFirstName(kb.nextLine().trim());
	        
	        System.out.print("Surname: ");
	        this.name.setSurname(kb.nextLine().trim());
	        
	        System.out.print("Phone Number: ");
	        this.phoneNumber = kb.nextLine().trim();

	        if (this.name.getTitle().isEmpty() || 
	            this.name.getFirstName().isEmpty() || 
	            this.name.getSurname().isEmpty() || 
	            this.phoneNumber.isEmpty()) {
	            throw new IllegalArgumentException("All fields are required.");
	        }

	        return true; // Successfully read
	    } catch (Exception e) {
	        System.out.println("Error reading Person details: " + e.getMessage());
	        return false; // Failure to read
	    }
	}
	
}
