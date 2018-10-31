-- Current implementation of Datamodel. May need a few more tweaks once we start applying it.

-- CREATE DATABASE NoteTakingApp;

--
-- Table structure for Users table
--

CREATE TABLE Users (
	AccountID int NOT NULL UNIQUE AUTO_INCREMENT,
    Username varchar(25) UNIQUE NOT NULL,
    Password varchar(25) NOT NULL,
    Email varchar(50) UNIQUE,
    FirstName varchar(25) NOT NULL,
    LastName varchar(25),
    PRIMARY KEY (AccountID)
);

--
-- Table structure for Status table
--

CREATE TABLE Status(
	StatusID int(1) NOT NULL UNIQUE DEFAULT 1,
    Description varchar(255) UNIQUE NOT NULL DEFAULT 'Note Page',
    PRIMARY KEY(StatusID)
);


--
-- Table structure for Category table
--

CREATE TABLE Category (
	CategoryID int NOT NULL AUTO_INCREMENT DEFAULT 1,
    AccountID int NOT NULL,
    CategoryName varchar(255) DEFAULT 'General Notes',
    PRIMARY KEY (CategoryID),
    FOREIGN KEY (AccountID)
		REFERENCES Users(AccountID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

--
-- Table structure for Notes table
--

CREATE TABLE Notes (
	NoteID int NOT NULL AUTO_INCREMENT,
    NoteContent varchar(255),
	Color varchar(255) DEFAULT '#ffffff',
    AccountID int,
    CategoryID int,
    StatusID int,
    PRIMARY KEY (NoteID),
    FOREIGN KEY (AccountID) 
		REFERENCES Users(AccountID) 
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY (CategoryID)
		REFERENCES Category(CategoryID)
        ON DELETE SET NULL     				-- Have to set null here apparently, will handle set default with some sort of alter table function in java
        ON UPDATE CASCADE,
	FOREIGN KEY (StatusID)
		REFERENCES Status(StatusID)
        ON DELETE SET NULL  				-- Same thing as above ^^
        ON UPDATE CASCADE
        
);
