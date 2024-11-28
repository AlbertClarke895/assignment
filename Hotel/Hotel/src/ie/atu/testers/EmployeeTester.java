/**
 * Class: B.Sc. in Computing
 * Instructor: Maria Boyle
 * Description: Tester for the Employee class
 * Date: dd/mm/yyyy
 * @author Albert Clarke
 * @version 1.0
 **/
package ie.atu.testers;

import java.text.DecimalFormat;
import java.util.ArrayList;

//import ie.atu.hotel.Date;
import ie.atu.hotel.Employee;
//import ie.atu.hotel.Name;

public class EmployeeTester {
	public static void main(String[] args) {
		// Create an ArrayList of Employees called employees
		ArrayList<Employee> employees=new ArrayList<Employee>();

		// Add 3 Employees to employees 
	    
		    
		
		    		      
		// Display all the Employee's in employees
		System.out.println("LIST OF EMPLOYEES");
		for(Employee employee:employees)
			System.out.println(employee);

		// Increment Employee's earning less than 30000
		System.out.println("");
		System.out.println("LIST OF EMPLOYEES AFTER SALARY INCREMENT");
		for(Employee employee:employees) {
			if(employee.getSalary() < 30000)
				employee.incrementSalary(1000);
		    System.out.println(employee);
		}

		// Display Employee's take home pay
		System.out.println("");
		System.out.println("EMPLOYEES TAKE HOME PAY");
		DecimalFormat df=new DecimalFormat("#.00");
		for(Employee employee:employees) {
			System.out.print(employee.getNumber() + "\t" + employee.getName());			
			System.out.println(employee.getSalary() < 35000 
				               ? "\t€" + df.format(employee.calculatePay(20))
				               : "\t€" + df.format(employee.calculatePay(40)));
		}
	}
}