# Tattoo App Backend

## Project Pitch
For our final project as part of the Java development bootcamp at Northcoders. A way we find out a lot of information 
about tattoo artist is them posting their work on various social media sites. However, if you are someone who wants to
find a tattoo artist or wants to find a tattoo design you might like this might mean traversing multiple sites which
are not set up for this particular problem. So we have made an app which can streamline a process for looking for a
tattoo which can show you where various artist are based.

## Overview Of the API

This is the backend API for an android app (see the front end 
[here](https://github.com/Spiersy95/splitface-frontend)) built using the Springboot framework. An 
instance of this API will be run on AWS with a postgres database. The main role of this API is to 
save all the tattoos and artist that are currently using and provide information to 
the front-end when required. 

## Structure of the database
There are four tables in our database holding all the data we need in the app which we will go over 
in this section.
### Artist Table
This contains information about the artist which signup to our app. The information is the following:

- Unique ID as a primary Key.
- Then name of the artist.
- A location for the artist where in the form of a postcode.
- An e-mail for the artist we will ask for this for login.
- A password (which has been hashed during a post request).
- A URL to hold the address of their profile picture in memory.

### Tattoo Table
This will hold the information about posts made by artist on the app each record will contain the 
following:

- A unique ID for each post.
- a URL which links to where the image of the post is held.
- The price of the tattoo.
- The ID of the artist who created it (Foreign Key).
- The hours which were worked on the tattoo.
- The time the post was created.

### Styles Table

- Each tattoo in our database will have styles associated with the tattoos this table will contain 
the following info:

- A unique ID for each Style
- A name for the style.

### A Tattoo Style Mapping Table

Each entry in this table is a tattoo ID and a style ID and this is used to establish the many-to-many relationship 
between each tattoo and style.

## HTTP Endpoints

Each of the models (Artist, style and Tattoo) in our app will each have their own controller, service and repositories.
For brevity, in this README we will just focus on the types of request we can make for each model.

### Artist Controller

#### GETs

- getAllArtist - return as a list of all the artist in the database
- getArtistById - returns an artist specified by the given ID
- passwordMatchWithEmail - this finds an artist if you provide the correct e-mail and password

#### POSTs

- addArtist - This adds an artist to the database and this will correctly hash the password provided for storage in database

#### PUTs

- editArtistDetails - Allows the ability to edit artist details by ID

#### DELETEs

- deleteArtistById - Allows the ability to delete artist by ID

### Tattoo Controller

#### GETs

- getAllTattoos - generates a list of all tattoos from the database.
- getAllTattoosByArtistId - this gives a list of all the associated tattoos to a given artist.

#### POSTs

- addTattooForArtistInDb - adds a tattoo associated to an artist in the db.

#### PUTs

- updateTattoo - allows the ability to edit the details of a tattoo.

#### DELETEs

- deleteTattooById - gives the ability to delete a specific tattoo By Id.

### Style Controller

#### GETs

- getAllStyles - returns all the valid styles in the database


### POSTs

- addStyle - add a style to the database
- addStylesForTattoo - Add a list of new styles to the database for a specific tattoo






