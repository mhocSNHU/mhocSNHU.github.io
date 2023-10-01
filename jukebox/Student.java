/*************************************************************************
 * @author Matthew Hocking
 * @file Student.java
 * @purpose Holds student info - This serves as an object to pass back and forth
 * between the database layer
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

public class Student {

	private String name; // Student name
	private int id; // Student ID
	private String user; // Username
	
	/****************************************************
	* Base level constructor for creating a student
	* 
	* @author Matthew
	* @category Artist Object
	* @param name: The string name of the student to be created
	* @param id: The int ID of the student
	* @param user: The username of the student
	****************************************************/
	public Student(String name, int id, String user) {
		this.name = name;
		this.id = id;
		this.user = user;
	}
	
	/****************************************************
	* Getter for name
	* 
	* @author Matthew
	* @category student Object
	* @return the String name of the given student
	****************************************************/
	public String getName() {
		return name;
	}

	/****************************************************
	* Setter for name
	* 
	* @author Matthew
	* @category student Object
	* param name: the name to change the student name to
	****************************************************/
	public void setName(String name) {
		this.name = name;
	}
	
	/****************************************************
	* Getter for ID
	* 
	* @author Matthew
	* @category student Object
	* @return the int ID of the given student
	****************************************************/
	public int getID() {
		return id;
	}

	/****************************************************
	* Setter for ID
	* 
	* @author Matthew
	* @category student Object
	* param name: the ID to change the student ID to
	****************************************************/
	public void setID(int ID) {
		this.id = ID;
	}
	
	/****************************************************
	* Getter for User
	* 
	* @author Matthew
	* @category student Object
	* @return the String username of the given student
	****************************************************/
	public String getUser() {
		return user;
	}

	/****************************************************
	* Setter for user
	* 
	* @author Matthew
	* @category student Object
	* param user: the user to change the student user to
	****************************************************/
	public void setUser(String user) {
		this.user = user;
	}
}
