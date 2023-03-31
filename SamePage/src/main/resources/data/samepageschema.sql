create database samepageschema;
use samepageschema;

CREATE TABLE IF NOT EXISTS Book (
    Title VARCHAR(200) NOT NULL,
    Author VARCHAR(200) NOT NULL,
    Description VARCHAR(10000),
    ISBN13 VARCHAR(500) NOT NULL,
    Img VARCHAR(200),
    Genre VARCHAR(200),
    PRIMARY KEY (ISBN13)
    );

CREATE TABLE IF NOT EXISTS Users (
    Username VARCHAR(200) NOT NULL UNIQUE,
    Email VARCHAR(200) NOT NULL UNIQUE,
    UserPassword VARCHAR(20) NOT NULL,
    UserID INT NOT NULL auto_increment,
    PRIMARY KEY (UserID)
    );

CREATE TABLE IF NOT EXISTS Favorites(
    BookID VARCHAR(500) NOT NULL,
    Username VARCHAR(200) NOT NULL,
    FavID INT NOT NULL,
    PRIMARY KEY (FavID, BookID),
    FOREIGN KEY (FavID) REFERENCES Users(UserID),
    FOREIGN KEY (BookID) REFERENCES Book(ISBN13)
    );

CREATE TABLE IF NOT EXISTS Reviews (
    BookID VARCHAR(500) NOT NULL,
    ReviewID INT NOT NULL AUTO_INCREMENT,
    ReviewDesc VARCHAR(500),
    Rating VARCHAR(1) NOT NULL,
    Username VARCHAR(20),
    DatePosted DATE not null,
    PRIMARY KEY (ReviewID),
    FOREIGN KEY (BookID) REFERENCES Book(ISBN13),
    FOREIGN KEY (Username) REFERENCES Users(Username)
    );

CREATE TABLE IF NOT EXISTS Goals (
   	UserID INT NOT NULL,
    Level INT NOT NULL DEFAULT 1,
   	NumOfBooksRead INT NOT NULL DEFAULT 1,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
   );

   -- Procedure to update user reading goals
DELIMITER DD
CREATE PROCEDURE UpdateGoals (IN UID INT)
    BEGIN
   	    DECLARE alreadyExists INT;
        DECLARE numBooksRead INT;
        DECLARE maxBooks INT;
        IF NOT EXISTS (SELECT * FROM Goals WHERE UserID = UID) THEN
   		    INSERT INTO Goals VALUES (UID, 1, 1);
        ELSE
   		    SET numBooksRead = (SELECT NumOfBooksRead FROM Goals WHERE UserID = UID);
            SET maxBooks = (SELECT Level FROM Goals WHERE UserID = UID) * 10;
   		    IF numBooksRead + 1 >= maxBooks THEN
               UPDATE Goals SET Level = ((maxBooks/10)+1) WHERE UserID = UID;
            END IF;
   		    UPDATE Goals SET NumOfBooksRead = NumOfBooksRead + 1 WHERE UserID = UID;
        END IF;
    END DD
DELIMITER ;