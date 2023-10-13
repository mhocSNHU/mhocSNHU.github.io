This artifact is a jukebox application created during the fall/summer of 2023 as part of a collaborative Git class. The project was initially built to have every single student, playlist, and artist in a separate Java class file. This made the program extremely difficult to expand upon and limited the total amount of variables that could be introduced before the project became bloated. My main two focuses for this project were to increase the UI complexity to allow the user to fully access the array of information available while also migrating all of the information to a database so that the information would be much easier to access.
This artifact was chosen for its inherent potential for expandability alongside the blatant problems that already existed within it. The initial version allowed the user to view playlists and nothing else. My software engineering category focused on expanding this functionality greatly to allow the user to dynamically interact with the database. This is supported by my focusing my database category on migrating the information held within the provided Java classes to a MySQL database.
Overall, this vastly reduced the complexity of the Java code and dropped the number of Java classes present greatly. Currently, the project allows the user to interact with nearly every part of the database all from a UI. The project itself has gone from allowing the user to view playlists to allowing the user to view, edit, add, and delete each of the data types with restrictions (students/playlists not owned by the user).
This artifact/milestone is also related to a variety of milestones. As I did not touch upon this very deeply in my software engineering submission, I will go into milestones related to both software engineering and databases here. I pointed out course goals 1, 3, and 5 as my primary goals for this artifact and I will touch briefly upon how each of them was met.
Course goal 1 was met in a variety of ways, chiefly through a vast amount of documentation. With course goal one focusing mainly on creating a project that has an emphasis on collaboration, I focused on trying to format the project so that someone could easily clone the project and begin making additions. Another example of this would be cloning the project and working on a new UI system for the project. Overall, this was achieved via significant amounts of in-line coding and the addition of a file that highlights the key functions and breaks it down into groups so that it's very easy to figure out what everything does.
Goal 3 was chiefly met by making steps to try and create a dynamic environment with the means to be expanded upon. I believe the most obvious example of working on a solution while managing tradeoffs in this specific artifact is the choice to use MySQL over NoSQL options. Although NoSQL would offer a much easier solution to adding a variable number of songs to a playlist, I found that it was very viable on MySQL and allowed me to make use of the faster queries in high-load environments of SQL systems. This helps set the foundation for creating a stable database when the amount of data in it is several hundred times more than my demo build. Although the problem would be much easier to solve in NoSQL, I found that it was very possible in MySQL. This allowed me to weigh the benefits and cons between the two database formats. It also gave me a chance to test myself and find a unique solution to a problem that would be very obvious given a different format.
Goal 5 was directly targeted by the inclusion of in-application security features. This comes in the form of a login system that prevents users from accessing playlists owned by another user. On top of this, I added extra smaller layers of security to help mitigate risk, such as hashing user passwords and cross-checking all playlist-related calls to ensure the playlist is owned by the current user. This helps create a more secure environment.
Targeting the database work specifically leads to me being able to talk about several components I learned a lot about. I learned how to make use of the Java to MySQL connector alongside learning much more about MySQL. I had not used MySQL in several years and this was a great opportunity to touch up my skills. Many of the main features and commands of MySQL were used throughout this artifact, so it gave me a well-rounded review. Alongside this, I was introduced to more Java tools, such as prepared statements. Overall, this should help me greatly with implementing SQL-based databases in the future.

<a href="https://mhocsnhu.github.io/narratives/HockingW5Nar.docx">Word Document Version</a>