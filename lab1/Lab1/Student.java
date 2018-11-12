package Lab1;
/** 	A class to hold student info
	<p>Last changed: Sept. 5, 2017
	<p>CS0445 Fall 2017, Lab 1	
	@author Sherif Khattab
	@version 0.1
*/
public class Student 
{
   private Name name;
   private String ID;

   /** Sets first name, last name, and ID to the empty string
	
   */
   public Student()
   {
	name = new Name();
	ID = "";
   }

   /** Parameterized constructor
	@param firstName a string for the first name
	@param lastName a string for the last name
	@param ID a string for the student ID
   */
   public Student(String firstName, String lastName, String ID)
   {
      name.setFirstName(firstName);
      name.setLastName(lastName);
      this.ID = ID;
   }

   /** Setter for the firstName instance variable
	@param firstName a string for the first name
   */
   public void setFirstName(String firstName)
   {
      name.setFirstName(firstName);      
   }

   /** Setter for the lastName instance variable
	@param lastName a string for the last name
   */
   public void setLastName(String lastName)
   {
      name.setLastName(lastName);      
   }

  /** Setter for the ID instance variable
	@param ID a string for the student ID
   */
   public void setID(String ID)
   {
	this.ID = ID;
   }


   /** Getter for the firstName instance variable
	@return the string for the first name
   */
   public String getFirstName()
   {
	return name.getFirstName();
   }

   /** Getter for the lastName instance variable
	@return the string for the last name
   */
   public String getLastName()
   {
	return name.getLastName();
   }

  /** Getter for the ID instance variable
	@return the string for the ID
   */
   public String getID()
   {
	return ID;
   }

}
