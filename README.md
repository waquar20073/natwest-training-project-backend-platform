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

