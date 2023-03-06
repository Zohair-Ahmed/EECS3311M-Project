CREATE TABLE Book (
                      Title VARCHAR(200) NOT NULL,
                      Author VARCHAR(200) NOT NULL,
                      Description VARCHAR(10000),
                      ISBN13 VARCHAR(500) NOT NULL,
                      Img VARCHAR(200),
                      Genre VARCHAR(200),
                      PRIMARY KEY (ISBN13)
);

CREATE TABLE Favorites(
                          BookID VARCHAR(500) NOT NULL,
                          Username VARCHAR(200) NOT NULL UNIQUE,
                          FavID INT,
                          PRIMARY KEY (FavID),
                          FOREIGN KEY (BookID) REFERENCES Book(ISBN13)
);

CREATE table Users (
                       Username VARCHAR(200) NOT NULL UNIQUE,
                       Email VARCHAR(200) NOT NULL UNIQUE,
                       UserPassword VARCHAR(20) NOT NULL,
                       FavID INT,
                       PRIMARY KEY (Email),
                       FOREIGN KEY (FavID) REFERENCES Favorites(FavID)
);

CREATE TABLE Reviews (
BookID VARCHAR(500) NOT NULL,
ReviewID INT NOT NULL,
ReviewDesc VARCHAR(500),
Username VARCHAR(20),
PRIMARY KEY (ReviewID),
FOREIGN KEY (BookID) REFERENCES Book(ISBN13),
FOREIGN KEY (Username) REFERENCES Users(Username)
);
