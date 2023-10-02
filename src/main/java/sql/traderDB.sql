CREATE DATABASE IF NOT EXISTS companies /*!40100 DEFAULT CHARACTER SET latin1 */;

USE companies;

DROP TABLE IF EXISTS COMPANY;
DROP TABLE IF EXISTS COMPANYxxx;
DROP TABLE IF EXISTS GAME;
DROP TABLE IF EXISTS PLAYER;

CREATE TABLE COMPANY(
 	COMPANYID INTEGER AUTO_INCREMENT PRIMARY KEY,
 	SYMBOL VARCHAR(20) NOT NULL,
 	COMPANYNAME VARCHAR(40) NOT NULL,
 	SHAREPRICE DOUBLE NOT NULL,
 	HIGH DOUBLE NOT NULL,
 	LOW DOUBLE NOT NULL
);

CREATE TABLE COMPANYxxx(
 	COMPANYID INTEGER NOT NULL,
 	SYMBOL VARCHAR(20) NOT NULL,
 	COMPANYNAME VARCHAR(40) NOT NULL,
 	SHAREPRICE DOUBLE NOT NULL,
 	HIGH DOUBLE NOT NULL,
 	LOW DOUBLE NOT NULL
);

CREATE TABLE PLAYER(
	PLAYERID INTEGER AUTO_INCREMENT PRIMARY KEY, 
	USERNAME VARCHAR(20) NOT NULL,
	PASSWORD VARCHAR(20) NOT NULL,
	LASTNAME VARCHAR(30) NOT NULL,
	FIRSTNAME VARCHAR(30) NOT NULL
);

CREATE TABLE GAME(
 	GAMEID INTEGER AUTO_INCREMENT PRIMARY KEY,
 	PLAYERID INTEGER,
 	BALANCE DOUBLE,
 	PERIOD DATE,
 	CONSTRAINT PLAYED_BY FOREIGN KEY (PLAYERID) REFERENCES PLAYER (PLAYERID)
);