create database samepageschema;
use samepageschema;

CREATE TABLE IF NOT EXISTS Book (
    Title VARCHAR(200) NOT NULL,
    Author VARCHAR(200) NOT NULL,
    Description VARCHAR(10000),
    ISBN13 VARCHAR(500) NOT NULL,
    Img VARCHAR(200),
    Genre VARCHAR(200),
    Likes INT DEFAULT 0,
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