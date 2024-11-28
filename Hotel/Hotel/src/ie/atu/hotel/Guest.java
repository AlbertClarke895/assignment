/**
 * Class: Software Implementation
 * Instructor: Maria Boyle
 * Description: Class Guest-Developed for the Hotel System
 * Date: dd/mm/yyyy
 * @author Albert Clarke
 * @version 1.0
 */
package ie.atu.hotel;

import java.io.Serializable;
import java.util.Scanner;


public class Guest extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
	// Guest has name & phoneNumber from Person
	private String emailAddress;    // AND an emailAddress
	private CreditCard creditCard;  // AND a creditCard	

	// Default Constructor
	
	public Guest() {
		super();	// Don't need to call super() explicitly.
		emailAddress="";
	}
	
	// Overloaded initialization constructor
	public Guest(Name name, String phoneNumber, String emailAddress, CreditCard creditCard) {
		super(name,phoneNumber);
		this.emailAddress=emailAddress;
		this.creditCard=creditCard;
	}

	// OVERRIDING the Person toString() method!
	@Override
	public String toString() {
		return super.toString() + "\t" + emailAddress + ".";
	}
	
	// get() and set() methods
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
		
	// read() method
	@Override
	public boolean read() {
	    System.out.println("GUEST DETAILS");
	    
	    // Call Person's read() method first
	    if (!super.read()) {
	        System.out.println("Failed to read base Person details.");
	        return false;
	    }

	    Scanner kb = new Scanner(System.in);
	    
	    try {
	        System.out.print("Email Address: ");
	        this.emailAddress = kb.nextLine().trim();
	        
	        if (this.emailAddress.isEmpty()) {
	            throw new IllegalArgumentException("Email Address is required.");
	        }

	        // Read credit card details using CreditCard.read()
	        System.out.println("Enter Credit Card Details:");
	        this.creditCard = CreditCard.read(); // Assuming CreditCard.read() returns a CreditCard object
	        if (this.creditCard == null) {
	            throw new IllegalArgumentException("Invalid credit card details.");
	        }

	        return true; // Successfully read all details
	    } catch (Exception e) {
	        System.out.println("Error reading Guest details: " + e.getMessage());
	        return false; // Failure to read
	    }
	}
}
