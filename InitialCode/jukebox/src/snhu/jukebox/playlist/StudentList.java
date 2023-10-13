package snhu.jukebox.playlist;

import snhu.student.playlists.*;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

	public StudentList(){
	}

	public List<String> getStudentsNames() {
		ArrayList<String> studentNames = new ArrayList<String>();
		
		String StudentName1 = "TestStudent1Name";
		studentNames.add(StudentName1);
		
		String StudentName2 = "TestStudent2Name";
		studentNames.add(StudentName2);
		
		/////////////////////////////////////////////////////////////////////////////
		// MODULE 5 CODE ASSIGNMENT                                                //
		// - Use examples above to add your name to the studenNames ArrayList      //
	    // - Add your code BELOW this comment block                                //
		/////////////////////////////////////////////////////////////////////////////
		
		// Additional Students
		
		// Added Trey Patten as a student
		String StudentName3 = "Trey Patten";
		studentNames.add(StudentName3);
		
		// Added Justin Byrd as a student
		String StudentName4 = "Justin Byrd";
		studentNames.add(StudentName4);
		
		// Added Matthew Hocking as a student
		String StudentName5 = "MatthewHocking";
		studentNames.add(StudentName5); 
		
		//Added Christopher Roelle as a student.
		String StudentName6 = "Christopher Roelle";
		studentNames.add(StudentName6);
		
		//Add Jeremy Cukrowski as a student.
		String StudentName7 = "Jeremy Cukrowski";
		studentNames.add(StudentName7); 
		
		//Add Anne Casey as a student.
		String StudentName8 = "Anne Casey";
		studentNames.add(StudentName8);
		
		return studentNames;
	}

	public Student GetStudentProfile(String student){
		Student emptyStudent = null;
	
		switch(student) {
		   case "TestStudent1_Playlist":
			   TestStudent1_Playlist testStudent1Playlist = new TestStudent1_Playlist();
			   Student TestStudent1 = new Student("TestStudent1", testStudent1Playlist.StudentPlaylist());
			   return TestStudent1;
			   
		   case "TestStudent2_Playlist":
			   TestStudent2_Playlist testStudent2Playlist = new TestStudent2_Playlist();
			   Student TestStudent2 = new Student("TestStudent2", testStudent2Playlist.StudentPlaylist());
			   return TestStudent2;
		
		   case "TreyPatten_Playlist":
			   TreyPatten_Playlist testStudent3Playlist = new TreyPatten_Playlist();
			   Student TestStudent3 = new Student("TestStudent3", testStudent3Playlist.StudentPlaylist());
			   return TestStudent3;
		   /////////////////////////////////////////////////////////////////////////////
		   // MODULE 6 CODE ASSIGNMENT                                                //
		   // - Use examples above to add your own case statement for your profile    //
		   // - Add your code BELOW this comment block                                //
		   /////////////////////////////////////////////////////////////////////////////
			   
		   // Matthew Hocking Playlist
		   case "MatthewHocking_Playlist":
			   Hocking_Playlist HockingPlaylist = new Hocking_Playlist();
			   Student TestStudent4 = new Student("MatthewHocking", HockingPlaylist.StudentPlaylist());
			   return TestStudent4;
			   
			   
			   
			   
			   
		}
		return emptyStudent;
	}
}
