package Lab1;
import java.util.Scanner;

/** 	A class to read and print a person's name
	<p>Last changed: Sept. 5, 2017
	<p>CS0445 Fall 2017, Lab 1	
	@author Sherif Khattab
	@version 0.1
*/
public class Main
{

   /** The entry-point to the program
	@param args an array of strings holding the command-line arguments	
   */
   public static void main(String[] args)
   {
	Name name = new Name();
	Scanner keyboard = new Scanner(System.in);
	System.out.print("Welcome to the program! Please enter your first name: ");
	name.setFirstName(keyboard.nextLine());
	System.out.print("Please enter your last name: ");
	name.setLastName(keyboard.nextLine());
	System.out.println("You entered: " + name.getFirstName() + " " + name.getLastName());
	
	Studnet id = new Studnet();
	System.out.println("Please enter your ID: ");
	id.setID(keyboard.nextLine());
	System.out.println("You entered: " + name.getFirstName() + " " + name.getLastName()+" "+id.getID());

	
   }
}
