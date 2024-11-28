/**
 * Class: B.Sc. in Computing
 * Instructor: Maria Boyle
 * Description: Models a Name
 * Date: dd/mm/yyyy
 * @author Albert Clarke
 * @version 1.0
**/
package ie.atu.hotel;

import java.io.*;

public class Name implements Serializable {
    private static final long serialVersionUID = 1L;
   private String title;
   private String firstName;
   private String surname;
   
   // Constructors to initialize the Instance Variables  
   public Name(){
	   this.title=this.firstName=this.surname="";
   }
   
   // Initialization Constructor
   public Name(String t, String fN, String sN) {
	   this.title=t;
	   this.firstName=fN;
	   this.surname=sN;
   }

   // toString() method
   @Override
   public String toString() {
	  return title + " " + firstName + " " + surname;
   }

   // equals() method
   @Override
   public boolean equals(Object obj){
	   Name nObject;
	   if (obj instanceof Name)
	      nObject = (Name)obj;
	   else
	      return false;
	 
	   return this.title.equals(nObject.title)
	       && this.firstName.equals(nObject.firstName)
	       && this.surname.equals(nObject.surname);
   }

   // get methods
   public String getTitle(){
      return title;
   }
   public String getFirstName(){
      return firstName;
   }
   public String getSurname(){
	  return surname;
   }
	
   // set methods
   public void setTitle(String setTitleTo){
      title=setTitleTo;
   }
   public void setFirstName(String setFirstNameTo){
      firstName=setFirstNameTo;
   }
   public void setSurname(String setSurnameTo){
      surname=setSurnameTo;
   }
   
   public boolean isFemale(){
	  if(title.equalsIgnoreCase("Miss") || 
		 title.equalsIgnoreCase("Ms") || 
		 title.equalsIgnoreCase("Mrs")) 
		    return true;
	   else
		    return false;
   }
}
