**Requirements:**
* npm
* maven
* jdk-17
* mysql-8

**Instructions to Run**:

1) Setup Database

	a) Install mysql-8 and run on port 3306

	b) Create user
	```
	create user 'group6'@'localhost' identified by '';
	```
	c) Grant permissions
	```
	grant all privileges on *.* to 'group6'@'localhost' with grant option;
	```
	d) Create database
	```
	create database user_db;
	```
2) Setup Mail Server  
	a) Install '*maildev*'
	```
	npm install -g maildev
	```
	b) Run '*maildev*'
	```
	maildev
	```
3) Run the spring boot application (will run on port 8085 by default)
	```
	mvn clean install
	mvn spring-boot:run
	```
4) After running the application, tables would be created, insert following rows in the database.    
	```
	insert into linked_banks(bankname,img,server_address) values("State Bank of India","SBI-logo.svg.png","localhost:5051");
	```
5) 
