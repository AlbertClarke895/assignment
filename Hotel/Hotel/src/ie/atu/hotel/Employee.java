package ie.atu.hotel;

import java.text.DecimalFormat;
//import java.util.Scanner;

import javax.swing.*;

import java.io.*;

public class Employee extends Person implements Payable, Serializable {
    private static final long serialVersionUID = 1L;

    private double salary;
    private int number;

	public static int nextNumber=10000;	// static for unique number - starts off at 1
	
	private final double MAX_SALARY = 150000;	

    // Default Constructor
	public Employee() {
	    super(); // Call Person's constructor
	    salary = 0.0;
	    // Don't assign or increment `number` here
	    number = 10000; // Temporary placeholder
	}
	
    // Initialization Constructor
    public Employee(Name name, String phoneNo, Date dob, Date startDate, double salary) {
        super(name, phoneNo); 
        this.salary = salary;
        this.number = nextNumber++;
    }
    
	// OVERRIDING the Person toString() method!
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return number + " " + name + " " + phoneNumber + " €" + df.format(salary);
    }

	// equals() method
	@Override
	public boolean equals(Object obj){
		Employee eObject;
		if (obj instanceof Employee)
		   eObject = (Employee)obj;
		else
		   return false;
		   
	    return(this.number==eObject.number);
	}


	public void setSalary(double salary){
		this.salary=salary;
	}
	public double getSalary(){
		return salary;
	}
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	
	
	public static int getNextNumber() {
        return nextNumber;
    }
	
	public static void setNextNumber(int nextNum) {
        nextNumber = nextNum;
    }
	
	
	// read() method
	@Override
	public boolean read() {
	    // Editable field for employee number
	    JTextField txtEmployeeNumber = new JTextField(String.valueOf(Employee.nextNumber));
	    // ComboBox for Title with predefined options
	    String[] titleOptions = {"Mr", "Ms", "Mrs", "Miss"};
	    JComboBox<String> cmbTitle = new JComboBox<>(titleOptions);

	    // Other fields
	    JTextField txtFirstName = new JTextField();
	    JTextField txtSurname = new JTextField();
	    JTextField txtPhoneNumber = new JTextField();
	    JTextField txtSalary = new JTextField();

	    // Construct the input dialog
	    Object[] message = {
	        "Employee Number:", txtEmployeeNumber,
	        "Title:", cmbTitle,
	        "First Name:", txtFirstName,
	        "Surname:", txtSurname,
	        "Phone Number:", txtPhoneNumber,
	        "Salary:", txtSalary,
	    };

	    JDialog dialog = new JDialog();
	    dialog.setAlwaysOnTop(true);

	    int option = JOptionPane.showConfirmDialog(
	        dialog,
	        message,
	        "Enter Employee Details",
	        JOptionPane.OK_CANCEL_OPTION
	    );

	    if (option == JOptionPane.OK_OPTION) {
	        try {
	            // Assign employee details only if all validations pass
	            this.number = Employee.nextNumber; // Assign the current employee number
	            this.name.setTitle((String) cmbTitle.getSelectedItem());
	            this.name.setFirstName(txtFirstName.getText().trim());
	            this.name.setSurname(txtSurname.getText().trim());
	            this.phoneNumber = txtPhoneNumber.getText().trim();

	            // Parse and validate salary
	            String salaryInput = txtSalary.getText().trim();
	            if (!salaryInput.isEmpty()) {
	                this.salary = Double.parseDouble(salaryInput);
	            } else {
	                this.salary = 0.0;
	            }

	            // Validate fields
	            if (this.name.getFirstName().isEmpty() ||
	                this.name.getSurname().isEmpty() ||
	                this.phoneNumber.isEmpty()) {
	                JOptionPane.showMessageDialog(
	                    dialog,
	                    "All fields except salary are required.",
	                    "Input Error",
	                    JOptionPane.ERROR_MESSAGE
	                );
	                return false;
	            }

	            // Increment the next employee number only on successful addition
	            Employee.nextNumber++;
	            return true;
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(
	                dialog,
	                "Salary must be a valid number.",
	                "Input Error",
	                JOptionPane.ERROR_MESSAGE
	            );
	            return false;
	        }
	    }
	    return false; // Return false if the user cancels
	}

	@Override
	public double calculatePay(double taxPercentage) {
		// return the monthly pay as salary/12 less taxPercentage
		double pay=salary/12;
		pay -= (pay * (taxPercentage/100));
		return pay;
	}

	@Override
	public double incrementSalary(double incrementAmount) {
		// add incrementAmount to, and return the new salary
		// salary should not go over a maximum salary of €150,000
		salary += incrementAmount;
		
		if(salary > MAX_SALARY)
			salary = MAX_SALARY;
		
		return salary;
	}
}