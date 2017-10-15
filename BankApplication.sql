
DROP SCHEMA IF EXISTS bank ;
CREATE SCHEMA IF NOT EXISTS bank DEFAULT CHARACTER SET utf8 ;
USE bank ;
DROP TABLE IF EXISTS bank.transactions;
DROP TABLE IF EXISTS bank.client_infos ;
CREATE TABLE IF NOT EXISTS bank.client_infos (

    accountNumber VARCHAR(20) PRIMARY KEY,
     balance DOUBLE(10 , 2 ) NOT NULL,
    pinCode INTEGER(4) NOT NULL,
    firstName VARCHAR(40),
    lastName VARCHAR(40),
    idNumber VARCHAR(9),
     phoneNumber VARCHAR(15),
    email VARCHAR(40),
    jmbg VARCHAR(13),
    status VARCHAR(3) default 'off'
); 
INSERT INTO client_infos (accountNumber,balance,pinCode,firstName,lastName,idNumber,jmbg,email,phoneNumber) VALUES
				('1111 2222 3333 4444',1000,1111,'Onid','Kurtovic','16CGH1156','111111111','nodspajid@hotmail.com','061 111 111'),
				('4444 3333 2222 1111',1000,2222,'Jasmina','Kurtovic','26CGH2256','2222222222222','jasmina98@hotmail.com','010 222 333'),
                ('1212 1212 1212 1212',500,3333,'David','Musliu','36CGH3356','3333333333333','david@hotmail.com','061 112 131');
                
INSERT INTO client_infos    (accountNumber,balance,pinCode,firstName,lastName,idNumber,jmbg,email,phoneNumber) VALUES
				('1234 1234 1234 1234',1000,2222,'Jasmina','Kurtovic','26CGH2256','2222222222222','jasmina98@hotmail.com','010 222 333');   
			
                
 CREATE TABLE IF NOT EXISTS transactions (
 
    id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    amount DOUBLE(10 , 2 ) NOT NULL,
    balance DOUBLE(10 , 2 ) NOT NULL,
    type_transaction VARCHAR(1),
    date_transaction  VARCHAR(30),
    accountNumber VARCHAR(20),
    FOREIGN KEY (accountNumber)
        REFERENCES client_infos (accountNumber)
);              

                
               