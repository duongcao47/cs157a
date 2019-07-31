DROP DATABASE IF EXISTS dev_airline_reservation;
CREATE DATABASE dev_airline_reservation;

USE dev_airline_reservation; 


---------------------TABLES-------------------------

DROP TABLE IF EXISTS User;
CREATE TABLE User (
    uID INT AUTO_INCREMENT,
    fname VARCHAR(30) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    email VARCHAR(80) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    birthDate DATE NOT NULL,
    age INT,
    isAdmin BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (uID)
); 

DROP TABLE IF EXISTS Airline;
CREATE TABLE Airline (
	alID INT AUTO_INCREMENT,
	companyName VARCHAR(50) NOT NULL UNIQUE,
	hq VARCHAR(50) NOT NULL,
	founded DATE NOT NULL,
	icao CHAR(3) NOT NULL UNIQUE,
	PRIMARY KEY (alID)
);

DROP TABLE IF EXISTS Plane;
CREATE TABLE Plane (
	pID INT AUTO_INCREMENT,
	manufacturer VARCHAR(50) NOT NULL,
	model VARCHAR(50) NOT NULL UNIQUE,
	capacity SMALLINT NOT NULL,
	PRIMARY KEY (pID)
);

DROP TABLE IF EXISTS Fleet;
CREATE TABLE Fleet (
	internalID INT AUTO_INCREMENT,
	alID INT,
	pID INT,
	FOREIGN KEY (pID) REFERENCES Plane(pID) ON DELETE CASCADE,
	PRIMARY KEY (internalID)
);

DROP TABLE IF EXISTS Country;
CREATE TABLE Country (
	name VARCHAR(100) NOT NULL,
	alphaTwoCode CHAR(2) NOT NULL UNIQUE,
	PRIMARY KEY (name)
);

DROP TABLE IF EXISTS Airport;
CREATE TABLE Airport (
	apID INT AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	city VARCHAR(50) NOT NULL,
	country VARCHAR(100) NOT NULL,
	iata CHAR(3),
	icao CHAR(4),
	lat DECIMAL(9, 6) NOT NULL,
	lng DECIMAL(9, 6) NOT NULL,
	altitude INT,
	tzOffSet TINYINT NOT NULL,
	dst CHAR(1),
	tz VARCHAR(50),
	type CHAR(7) NOT NULL,
	source CHAR(11) NOT NULL,
	FOREIGN KEY (country) REFERENCES Country(name),
	PRIMARY KEY (apID)
);

DROP TABLE IF EXISTS FlightPath;
CREATE TABLE FlightPath (
	fpID INT AUTO_INCREMENT,
	departure INT NOT NULL,
	destination INT NOT NULL,
	CHECK(departure <> destination),
	FOREIGN KEY (departure) REFERENCES Airport(apID) ON DELETE CASCADE,
	FOREIGN KEY (departure) REFERENCES Airport(apID) ON DELETE CASCADE,
	PRIMARY KEY (fpID)
);

DROP TABLE IF EXISTS Flight;
CREATE TABLE Flight (
	fID INT AUTO_INCREMENT,
	internalID INT, 
	fpID INT,
	duration INT,
	departureDate DATE NOT NULL,
	price INT,
	seatsAvailable SMALLINT NOT NULL DEFAULT 141,
	updatedOn timestamp not null on update current_timestamp,
	UNIQUE(internalID, fpID, departureDate),
	FOREIGN KEY (internalID) REFERENCES Fleet(internalID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (fpID) REFERENCES FlightPath(fpID) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (fID)
);

DROP TABLE IF EXISTS ArchivedFlight;
CREATE TABLE ArchivedFlight (
	fID INT AUTO_INCREMENT,
	internalID INT, 
	fpID INT,
	duration INT,
	departureDate DATE NOT NULL,
	price INT,
	seatsAvailable SMALLINT NOT NULL DEFAULT 141,
	updatedOn timestamp not null on update current_timestamp,
	UNIQUE(internalID, fpID, departureDate),
	FOREIGN KEY (internalID) REFERENCES Fleet(internalID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (fpID) REFERENCES FlightPath(fpID) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (fID)
);

DROP TABLE IF EXISTS Trip;
CREATE TABLE Trip (
	uID INT,
	fID INT,
	FOREIGN KEY (uID) REFERENCES User(uID) ON DELETE CASCADE,
	FOREIGN KEY (fID) REFERENCES Flight(fID) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (uID, fID)
);


---------------------TRIGGERS-------------------------

DROP TRIGGER IF EXISTS userAgeTrigger;
CREATE TRIGGER userAgeTrigger
BEFORE INSERT ON User
FOR EACH ROW
SET NEW.age = CURRENT_DATE - NEW.birthDate; 

DROP TRIGGER if EXISTS randomFlightDurationTrigger;
CREATE TRIGGER randomFlightDurationTrigger
BEFORE INSERT ON Flight
FOR EACH ROW
SET NEW.duration = Rand()*(18000-3600)+3600, NEW.price = Rand()*(50000-7000)+7000;


---------------------PROCEDURES-------------------------


---------------------INSERTS-------------------------

INSERT INTO USER (fname, lname, email, password, birthDate, isAdmin) VALUES ("Riley", "Chetwood", "rc@gmail.com", "pass123", "1993-7-02", true);
INSERT INTO USER (fname, lname, email, password, birthDate, isAdmin) VALUES ("Luz", "Hernandez", "lh@gmail.com", "pass321", "1994-06-28", false);
INSERT INTO USER (fname, lname, email, password, birthDate, isAdmin) VALUES ("Harry", "Potter", "hp@yahoo.com", "YerAWizard", "1980-07-31", false);
INSERT INTO USER (fname, lname, email, password, birthDate, isAdmin) VALUES ("Frodo", "Baggins", "fb@yahoo.com", "HobbitsToIsengrad", "1929-07-31", false);

INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Alaska Airlines", "ASA", "Seatle", "1932-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Allegiant Air", "AAY", " Las Vegas", "1997-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("American Airlines", "AAL", "Dallas", "1926-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Delta Airlines", "DAL", "Atlanta", "1924-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Frontier Airlines", "FFT", "Denver", "1994-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Hawaiian Airlines", "HAL", "Honolulu", "1929-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("JetBlue Airways", "JBU", "New York", "1998-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Southwest Airlines", "SWA", "Dallas", "1967-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Spirit Airlines", "NKS", "Fort Lauderdale", "1980-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("Sun Country Airlines", "SCX", "St. Paul", "1982-01-01");
INSERT INTO Airline (companyName, icao, hq, founded) VALUES ("United Airlines", "UAL", "Chicago", "1926-01-01");

-- HAVE TO CHANGE THIS TO MATCH CORRECT PATH
-- ALL AIRPORT DATA WAS FOUND AT https://openflights.org/data.html

-- the text files need to be in a datadir defined in mysqls my.cfg
-- said directory can be found using the following command: 
-- SHOW VARIABLES WHERE Variable_Name LIKE "%dir";
LOAD DATA INFILE 'plane.txt' into table plane;
LOAD DATA INFILE 'country.txt' into table country;
LOAD DATA INFILE 'airport.txt' into table airport;

INSERT INTO FlightPath (departure, destination) 
SELECT * 
FROM (SELECT apID FROM Airport WHERE country='United States' GROUP BY tz) A1 JOIN 
(SELECT apID FROM Airport WHERE country='United States' GROUP BY tz) A2 
WHERE A1.apID <> A2.apID; 

INSERT INTO Fleet (alID, pID)
SELECT AL.alID, P1.pID 
FROM airline AL JOIN plane P1 JOIN plane P2 
where rand() < 0.4;

INSERT INTO Flight (internalID, fpID, departureDate) 
SELECT F.internalID, FP.fpID, D.departureDate 
FROM (SELECT internalID FROM Fleet) F, (SELECT fpID FROM FlightPath) FP, (SELECT '2019-08-05' departureDate) D 
WHERE Rand() > 0.999;

 INSERT INTO Trip 
 SELECT U.uID, (Rand()*(MAX(F.fID)-MIN(F.fID))+MIN(F.fID)) 
 FROM (SELECT uID FROM User) U, (SELECT fID FROM Flight) F 
 GROUP BY uID;

