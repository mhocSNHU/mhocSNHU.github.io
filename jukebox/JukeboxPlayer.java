/*************************************************************************
 * @author Matthew Hocking
 * @file JukeboxPlayer.java
 * @purpose Serves as the main loop holder - This is the main loop that is used to
 * call the sample user interface
 * @institution SNHU
 *************************************************************************/
package snhu.jukebox.playlist;

public class JukeboxPlayer {
         
    public static void main(String[] args) {
        
    	try{
    		// Setup
			CommandManager cm=new CommandManager(); 		// Generates the command manager used to process all input
			Boolean play = cm.loginProcess();       		// Login with a user to determine what can be seen
			
			// Main Loop
			while(play){                            	    // Main loop
				cm.printMainMenu();                         // Prints out a version of the main menu
				String command = cm.getCommand();           // Gets a command to process from the command manager            	
				if(command.toLowerCase().equals("quit"))	// If the input is quit, then exit the main loop                	
					break;                                  
				 cm.processCommand(command);    			// Process the command given the user input
			

			}
    	}catch(Exception e){
			System.out.println("Oops!! Something went wrong. Please try again!!");
			e.printStackTrace();
		}  
    }
}