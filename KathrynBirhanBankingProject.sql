/*******************************************************************************
   Chinook Database - Version 1.4
   Script: Chinook_Oracle.sql
   Description: Creates and populates the Chinook database.
   DB Server: Oracle
   Author: Luis Rocha
   License: http://www.codeplex.com/ChinookDatabase/license
********************************************************************************/

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
DROP USER chinook CASCADE;

/*******************************************************************************
   Create database
********************************************************************************/
CREATE USER BankProject
IDENTIFIED BY p4ssw0rdkb
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to BankProject;
GRANT resource to BankProject;
GRANT create session TO BankProject;
GRANT create table TO BankProject;
GRANT create view TO BankProject;



conn BankProject/p4ssw0rdkb


/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE LEDGER (
    USER_ID INTEGER PRIMARY KEY, 
    USERNAME VARCHAR2(100),
    PASS VARCHAR2(100),--ADDING PK TO THIS COLUMN
    FIRSTNAME VARCHAR2(100),
    LASTNAME VARCHAR2(100)
);

CREATE TABLE ACCOUNTS (
ACCOUNT_ID INTEGER PRIMARY KEY,
ACCOUNT_TYPE VARCHAR2 (100),
BALANCE NUMBER (9,2) DEFAULT 0.00 CHECK (BALANCE > -1),
USER_ID INTEGER NOT NULL
    
);
/
CREATE TABLE TRANSACTIONS (
TRANSACTION_ID INTEGER PRIMARY KEY,
ACCOUNT_ID INTEGER NOT NULL,
TRANSACTION_TIMESTAMP TIMESTAMP WITH TIME ZONE,
TRANSACTION_AMOUNT NUMBER (9,2) DEFAULT 0.00
)
/
--FOREIGN KEY CONSTRAINTS
--CONSTRAINT: RULE PLACED ON THE CONTENTS OF A TABLE, LIMITS WHAT MAY BE INSERTED
--TYPES - PK, FK, UNIQUE, CHECK, NOT NULL

ALTER TABLE ACCOUNTS
ADD CONSTRAINT FK_ACCOUNTS_USER_ID
FOREIGN KEY (USER_ID) REFERENCES LEDGER(USER_ID);
/
ALTER TABLE LEDGER
ADD CONSTRAINT UNIQUE_USERS
UNIQUE (USERNAME);
/
ALTER TABLE TRANSACTIONS
ADD CONSTRAINT FK_TRANSACTIONS_ACCOUNT
FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS(ACCOUNT_ID);

/
CREATE SEQUENCE SQ_LEDGER_PK
START WITH 2000
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_LEDGER
BEFORE INSERT ON LEDGER
FOR EACH ROW
BEGIN
    SELECT SQ_LEDGER_PK.NEXTVAL INTO : NEW.USER_ID FROM DUAL;
END;    
/
CREATE or replace SEQUENCE SQ_ACCOUNTS_PK
START WITH 2000
INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNTS
BEFORE INSERT ON ACCOUNTS
FOR EACH ROW
BEGIN
    SELECT SQ_ACCOUNTS_PK.NEXTVAL INTO : NEW.ACCOUNT_ID FROM DUAL;
END; 
/
CREATE OR REPLACE PROCEDURE SP_DELETE_YO_ACCOUNT (USERNUM IN INT)
   
IS
BEGIN
    DELETE ACCOUNTS WHERE ACCOUNT_ID = USERNUM;
END;

/
CREATE OR REPLACE PROCEDURE SP_DELETE_A_USER (USERNUM IN INT)
   
IS
BEGIN
    DELETE LEDGER WHERE USER_ID = USERNUM;
END;
/

CREATE OR REPLACE PROCEDURE SP_DELETE_ALL_THE_ACCOUNTS 
   
IS
BEGIN


    DELETE ACCOUNTS WHERE ACCOUNT_ID > 1;

END;
/
