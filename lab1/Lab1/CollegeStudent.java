package Lab1;
/** 	A class to hold college student info. A college student is a student and has an extra field for the standing (Freshman, Sophomore, Junior, or Senior).
	<p>Last changed: Sept. 5, 2017
	<p>CS0445 Fall 2017, Lab 1	
	@author Sherif Khattab
	@version 0.1
*/
public class CollegeStudent extends Student//update name to use studnet class,student ID as well
{
   private String standing;
   //freshman.....so on
   //create standing.java with stainding in it
   //standing.frshman
   //update main to use college student-first last name Student ID and standing
   /** Sets first name, last name, ID, and standing to the empty string	
   */
   public CollegeStudent()
   {
	    super();
	    standing = "";
   }
  /** Parameterized constructor
	@param firstName a string for the first name
	@param lastName a string for the last name
	@param ID a string for the student ID
	@param standing a string for the student standing
   */
   public CollegeStudent(String firstName, String lastName, String ID, String standing)
   {
	  super(firstName, lastName, ID);
 	  this.standing = standing;
   }

   /** Setter for the standing instance variable
	@param standing a string for the standing
   */
   public void setStanding(String standing)
   {
      this.standing = standing;      
   }

  

   /** Getter for the standing instance variable
	@return the string for the student standing
   */
   public String getStanding()
   {
	return standing;
   }
}
