# Jukebox Application

**Preface:**

This document will break down the functions and use cases of the DB connector for the jukebox application alongside the functionality of the provided user interface. Despite there being a provided UI, the application is built to allow for new user interfaces to be built around the database class.

**The Database:**

The database class is built around a backend database. This section will go over both the tables in said database and how to make use of the provided database class.

**Database Tables:**

![Database Tablevs](/assets/images/DB.png)

Artists:

```
artistID	artistNAME

The artistID serves as the primary key for the artist table while the artistNAME is the only descriptive component 
of the artist table. This table is designed to be expandable, so other artist attributes would be easy to add
as long as the database file was properly updated.

Examples of new attributes could be addition date and number of songs.
```

Songs:

```
songsID	songARTIST	songNAME

The songs table is used to hold the individual information for each song. This table is separate
from the artist table to create a clear connection where every artist can have many songs. This
table consists of songsID, songARTIST, and songNAME. The songsID is the primary key while songARTIST
is a foreign key linked to the related artist. Alongside these two values is the songNAME, which
is the only current piece of information unique to each song.
```

Students:

```
studentsID	studentsName	studentsPASS	studentUSER

The student pass holds the login information for each student. Certain pieces of information (playlists)
are unique to each student. Keeping each student's information secure is important. The studentsID is the
primary key of the table while the studentNAME and studentUSER hold the selected name and user for each
individual student. Alongside this, we hold a studentsPASS, which is a hashed password used to log in.
```

Playlists:

```
playlistsID	playlistsSTUDENT	playlistsNAME

This table is meant to hold the playlist ownership and quantity. This table is NOT meant to hold individual song info,
hence there are no song related columns present. The columns that are present are the playlistsID column,
which serves as the primary key alongside the playlistsSTUDENT column, 
which reperesents a foreign key to connect playlist ownership to an individual student. There is also the playlistsNAME column,
which holds the string identifier for what to call the playlist.
```

Entries:

```
entryID	entryPLAYLIST	entrySONG

The entry table is used as the connection that binds the playlist to the individual songs it must represent. 
The entryID serves as the primary key while the entryPLAYLIST and entrySONG columns are foreign keys to 
connect a given playlist to a song. This system allows for each playlist to
connect to many different songs.
```

**Database Class:**

The database class is built to provide the user with easy access to the database while using the associated classes (artist/song/student). The database class has its functionality broken down into the individual classes they interact with. This means that the provided functions are all broken down into the following groups:

```
Artists – Interacts with the artist table

Songs – Interacts with the song table

Playlist – Interacts with the playlist and entries tables

User – Interacts with the user table
```

Alongside these categories are a variety of uses that each function carries. Every function is named as (db)(category)(use case) unless it’s a full retrieve function, which is just (db)(category). 

It must also be noted that any use of the database class must have the MySQL to java connector in the classpath.

Every function makes use of one of the following call types. 

INSERT:
```
dbAddArtist – Creates a new artist in the DB based on the inputted artist

dbAddSong – Creates a new song in the DB based on a given song object

dbAddPlaylist – Creates a new playlist in the DB given a playlist object

dbFillEntries – Creates new entries based on the given a playlist and an ID 

dbAddEntry – Creates a single new entry for a playlist based on a playlist ID and song ID

dbNewUser – Creates a new user in the DB based on a given user object
```

DELETE:
```
dbDeleteArtist – Removes an artist from the DB given its ID

dbDeleteSong – Removes a song from the DB given its ID

dbDeletePlaylist – Removes a playlist from the DB given its ID

dbRemoveEntry – Removes a single song from the entry table given the playlist ID and song ID
```
	    
SELECT:
```
dbArtists – Returns every single artist in an arraylist

dbSongs – Returns every single song in an arraylist

dbSongsByArtist – Returns every song related to the given artist ID

dbSongsByID – Returns the given song info related to the given song ID

dbPlaylists – Prints out every playlist owned by the given user

dbPlaylistRetrieve – Retrieves the info for a given playlist

dbUserCheck – Checks to see if a given username / password combo exist in the DB

dbUserDupCheck – Checks to see if a given username exists in the DB
```
 
UPDATE:
```
dbSetArtistName – Change the name of a pre-existing artist given its ID and a new name

dbSetSongName – Change the name of a pre-existing song given its ID and a new name

dbSetPlaylistName - Change the name of a pre-existing playlist given its ID and a new name
```
 



**User Interface:**
The given user interface provides the user with ways to interact with every primary component of the database. The provided UI is broken into two components. These two components are the login and menu components. 

Login Commands:

![Login Interface](/assets/images/Login.png)

```
quit – Exit App

existing – Login for an existing user

new – Create a new user
```

Menu:

![Main Interface](/assets/images/MainMenu.png)

```
new artist – create new artist

new playlist – create new playlist

new song – create new song

view artist – view all artists

view playlist – view all playlists

view song – view all songs

edit playlist – Directs to the playlist editing UI

edit song – Directs to the song editing UI

edit artist – Directs to the artist editing UI

play – play a playlist

quit - quit
```


Jukebox Codebase:

<a href="https://github.com/mhocSNHU/mhocSNHU.github.io/tree/main/jukebox"> Codebase</a>

Narratives:

Software Engineering Component:

<a href="https://mhocsnhu.github.io/narratives/HockingW3Nar"> Week 3 Narrative</a>

Datebase Component:

<a href="https://mhocsnhu.github.io/narratives/HockingW5Nar"> Week 5 Narrative</a>
