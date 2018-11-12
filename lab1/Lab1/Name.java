package Lab1;
/** 	A class to hold first name and last name of a person 
	<p>Last changed: Sept. 5, 2017
	<p>CS0445 Fall 2017, Lab 1	
	@author Sherif Khattab
	@version 0.1
*/
public class Name 
{
   private String firstName;
   private String lastName;

   /** Sets first name and last name to the empty string*/
   public Name()
   {
      firstName = "";
      lastName = "";
   }

   /** Parameterized constructor
	@param firstName a string for the first name
	@param lastName a string for the last name
   */
   public Name(String firstName, String lastName)
   {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   /** Setter for the firstName instance variable
	@param firstName a string for the first name
   */
   public void setFirstName(String firstName)
   {
       name setFirstName(firstName);
      this.firstName = firstName;      
   }

   /** Setter for the lastName instance variable
	@param lastName a string for the last name
   */
   public void setLastName(String lastName)
   {
      this.lastName = lastName;      
   }

   /** Getter for the firstName instance variable
	@return the string for the first name
   */
   public String getFirstName()
   {
	return firstName;
   }

   /** Getter for the lastName instance variable
	@return the string for the last name
   */
   public String getLastName()
   {
	return lastName;
   }

}
