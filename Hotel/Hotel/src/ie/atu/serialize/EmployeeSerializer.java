package ie.atu.serialize;

import java.io.*;
import java.util.*;
import javax.swing.*;
import ie.atu.hotel.Employee;



public class EmployeeSerializer {
	private ArrayList<Employee> employees;
	
	private final String FILENAME = "employees.bin";	
	private File employeesFile;	
	
	// Default Constructor
	public EmployeeSerializer() {
	    employees = new ArrayList<>(); // Initialize the ArrayList
	    employeesFile = new File(FILENAME); // Reference to the employees.bin file

	    try {
	        if (!employeesFile.exists()) {
	            employeesFile.createNewFile(); // Create the file if it doesn't exist
	        } else {
	            deserializeEmployees(); // Load existing employees from the file
	        }

	        if (!employees.isEmpty()) {
	            int highestNumber = employees.stream()
	                                         .mapToInt(Employee::getNumber)
	                                         .max()
	                                         .orElse(Employee.nextNumber);
	            Employee.setNextNumber(highestNumber + 1); // Ensure the next number is correct
	        }
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Error initializing file: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////
	// Method Name : add()								              //
	// Return Type : void								              //
	// Parameters : None								                 //
	// Purpose : Reads one Employee record from the user       //
	//           and adds it to the ArrayList called employees //
	/////////////////////////////////////////////////////////////
	public void add() {
	    Employee employee = new Employee();
	    if (employee.read()) {
	        employees.add(employee);
	        JOptionPane.showMessageDialog(null, "Employee added successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "Employee not added. Operation canceled or invalid input.", "CANCELED", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////
	// Method Name : list()						              //
	// Return Type : void					   	           //
	// Parameters : None						                 //
	// Purpose : Lists all Employee records in employees //
	///////////////////////////////////////////////////////		
	public void list(){
		String employeesToList="";

		if(!employees.isEmpty()) {
			//every Employee object in employees from the serializer 
			for(Employee tmpEmployee : employees) {
				// adding it to employeesToList 
				employeesToList+=tmpEmployee;
				// add a new line
				employeesToList+="\n";
			}
			
			// Display employeesToList in a messageDialog
		   JOptionPane.showMessageDialog(null, employeesToList, "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);	
		}
		else
			// Display "No Employees stored..." in messageDialog
		   JOptionPane.showMessageDialog(null, "No Employees to list.", "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);	
	}	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////
	// Method Name : view()									              //
	// Return Type : Employee								              //
	// Parameters : None								                    //
	// Purpose : Displays the required Employee record on screen  //
	//         : And returns it, or null if not found             //   
	////////////////////////////////////////////////////////////////	
	public Employee view() {
	    try {
	        //user input for the Employee number
	        String input = JOptionPane.showInputDialog(null, "Enter Employee Number:", "VIEW EMPLOYEE", JOptionPane.QUESTION_MESSAGE);
	        
	        if (input == null || input.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Employee number is required.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	            return null; // User canceled or provided invalid input
	        }
	        
	        int empNumber = Integer.parseInt(input.trim()); // Parse the number
	        for (Employee employee : employees) { // Iterate over the ArrayList
	            if (employee.getNumber() == empNumber) { // Compare Employee numbers
	                JOptionPane.showMessageDialog(null, employee, "EMPLOYEE DETAILS", JOptionPane.INFORMATION_MESSAGE);
	                return employee; // Return the found Employee
	            }
	        }
	        
	        // If no match is found
	        JOptionPane.showMessageDialog(null, "Employee not found with number: " + empNumber, "NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
	        return null;
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid employee number.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	    return null; // Return null in case of any issues
	}
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////
	// Method Name : delete()							        	           //
	// Return Type : void								        	           //
	// Parameters : None									                    //
	// Purpose : Deletes the required Employee record from employees //
	///////////////////////////////////////////////////////////////////	
	public void delete() {
	    try {
	        //user input for the Employee number
	        String input = JOptionPane.showInputDialog(null, "Enter Employee Number to Delete:", "DELETE EMPLOYEE", JOptionPane.QUESTION_MESSAGE);

	        if (input == null || input.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Employee number is required.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	            return; // User canceled or provided invalid input
	        }

	        int empNumber = Integer.parseInt(input.trim()); // Parse the number

	        // Find the Employee in the list
	        for (Employee employee : employees) {
	            if (employee.getNumber() == empNumber) {	                
	                    employees.remove(employee); // Remove the employee from the list
	                    serializeEmployees(); // Save updated list to file
	                    JOptionPane.showMessageDialog(null, "Employee deleted successfully and changes saved to file!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Delete operation canceled.", "CANCELED", JOptionPane.WARNING_MESSAGE);
	                }
	                return; // Exit after deletion
	            }
	        
	        // If no match is found
	        JOptionPane.showMessageDialog(null, "Employee not found with number: " + empNumber, "NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid employee number.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////
	// Method Name : edit()			  					                //
	// Return Type : void								    	          //
	// Parameters : None								     	             //
	// Purpose : Edits the required Employee record in employees //
	///////////////////////////////////////////////////////////////	
	public void edit() {
	    try {
	        //user input for the Employee number
	        String input = JOptionPane.showInputDialog(null, "Enter Employee Number to Edit:", "EDIT EMPLOYEE", JOptionPane.QUESTION_MESSAGE);

	        if (input == null || input.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Employee number is required.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	            return; // User canceled or provided invalid input
	        }

	        int empNumber = Integer.parseInt(input.trim()); // Parse the number

	        // Find the Employee in the list
	        for (Employee employee : employees) {
	            if (employee.getNumber() == empNumber) {
	                // editable fields with pre-filled details from existing Employee details
	                JTextField txtNumber = new JTextField(String.valueOf(employee.getNumber()));
	                txtNumber.setEditable(false); // remove the ability to edit employee number
	                JComboBox<String> cboTitle = new JComboBox<>(new String[]{"Mr", "Ms", "Mrs", "Miss"});
	                cboTitle.setSelectedItem(employee.getName().getTitle());
	                JTextField txtFirstName = new JTextField(employee.getName().getFirstName());
	                JTextField txtSurname = new JTextField(employee.getName().getSurname());
	                JTextField txtPhoneNumber = new JTextField(employee.getPhoneNumber());
	                JTextField txtSalary = new JTextField(String.format("%.2f", employee.getSalary()));

	                Object[] message = {
	                    "Employee Number (not editable):", txtNumber,
	                    "Title:", cboTitle,
	                    "First Name:", txtFirstName,
	                    "Surname:", txtSurname,
	                    "Phone Number:", txtPhoneNumber,
	                    "Salary:", txtSalary
	                };

	                // Show the dialog for editing
	                int option = JOptionPane.showConfirmDialog(null, message, "EDIT EMPLOYEE DETAILS", JOptionPane.OK_CANCEL_OPTION);
	                if (option == JOptionPane.OK_OPTION) {
	                    try {
	                        // Update the Employee details
	                        employee.getName().setTitle(cboTitle.getSelectedItem().toString());
	                        employee.getName().setFirstName(txtFirstName.getText().trim());
	                        employee.getName().setSurname(txtSurname.getText().trim());
	                        employee.setPhoneNumber(txtPhoneNumber.getText().trim());
	                        employee.setSalary(Double.parseDouble(txtSalary.getText().trim()));

	                        JOptionPane.showMessageDialog(null, "Employee updated successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
	                    } catch (NumberFormatException e) {
	                        JOptionPane.showMessageDialog(null, "Salary must be a valid number.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	                    } catch (Exception e) {
	                        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	                return; // Exit after editing
	            }
	        }

	        // If no match is found
	        JOptionPane.showMessageDialog(null, "Employee not found with number: " + empNumber, "NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid number format. Please enter a valid employee number.", "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	
	
	
	
	
	// This method will serialize the employees ArrayList when called
	public void serializeEmployees() {
	    try (ObjectOutputStream serialize = new ObjectOutputStream(new FileOutputStream(employeesFile))) {
	    	serialize.writeObject(employees); // Write the employees list to the file
	        JOptionPane.showMessageDialog(null, "Employees successfully saved to file.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Error saving employees: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	
	
	
	
	
	
	// This method will deserialize the Employees ArrayList when called
	public void deserializeEmployees(){
		 ObjectInputStream is=null;
		
		 try {
			 // Deserialize the ArrayList...
			 FileInputStream fileStream = new FileInputStream(employeesFile);
				
			 is = new ObjectInputStream(fileStream);
						
			 employees = (ArrayList<Employee>)is.readObject();
          
          is.close();
		}
		catch(FileNotFoundException fNFE){
			 System.out.println("Cannot open file " + employeesFile.getName() + ".");
		}
		catch(IOException ioE){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
		catch(Exception e){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
	}
}