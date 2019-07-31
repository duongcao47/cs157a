# cs157aFinal

The following project must have a hibernate.properties file with the following values: <br/>
hibernate.connection.url= // the url to the db <br/>
hibernate.connection.username= // user name <br/>
hibernate.connection.password= // password <br/>
projectPath= // the path to sql script to execute <br/>

Additionally, in order to get ride of the LOAD DATA INFILE pathing issue <br/>
the project now requires that the .txt files in src/main/resources/Airline-Reservation <br/>
be in the directory specified by mysql 'datadir' to see where this directory is <br/>
open a mysql shell and run the following command: <br/>

SHOW VARIABLES WHERE Variable_Name LIKE "%dir"; <br/>