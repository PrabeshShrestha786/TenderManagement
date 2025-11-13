Technologies used:-
Front-End Development:
	Html
	Css
	Javascript
	BootStrap
Back-End Development
	Java
	JDBC
	Servlet
	JSP
	MySQL
==== Software And Tools Required ======
	: MySQL
	: Eclipse EE
	: Java JDK 8+
	: Tomcat v8.0
	: Apache Maven
	
	
====== Importing and Running the Project Through Eclipse EE ===========
Step 0: Open Eclipse Enterprise Edition. [Install if not available]

Step 1: Click On File > Import > Git > Projects From Git > Clone Uri > Paste The Repository Url: https://github.com/PrabeshShrestha786/TenderManagement.git > Next > Select Master Branch > Select Tender-Management-System\tendermanagement (Eclipse Project) > Next > Finish

Step 2.a: Go inside tendermanagement > Java Resources > dbdetails.properties and update the value of username and password according to your installed mysql admin credentials

Step 2.b: Right Click on Project > Run as > Maven Build > In the goals field enter "clean install" > apply > run

Step 3: Right click on Project > Maven > Update Project > Select Project Name > Select Force Update > Update

Step 4.1: [Only if Tomcat v8.0 is not Configured in Eclipse]: Right Click On Project > Run As > Run On Server > Select Tomcat v8.0 > (Select Tomcat V8.0 Installation Location If Asked) Next > Add > Finish

Step 4.2: In The Server Tab > Double Click On Tomcat Server > Ports > Change The Port Number For Http/1.1 To 8083 > Close And Save

Step 5: Right Click On Project > Run As > Run On Server > Select Tomcat V8.0 > Next > Add All> Done

Step 6: Check Running The Site At http://localhost:8083/tendermanagement/ (Best Viewed in chrome desktop version)

Step 7: Default Username And Password For Admin Is "Admin" and "Admin" respectively. [Case-Sensitive]	
	
	
	
========== Dummy Database Initialization USing SQLDUMP =====================
STEPS: If you want to create table structure with dummy database.
create database tender;

commit;

use tender;

create table notice(id int(3) not null auto_increment, title varchar(35),info varchar(300), primary key(id));

alter table notice AUTO_INCREMENT = 1;


create table vendor(vid varchar(15) primary key,password varchar(20),vname varchar(30),vmob varchar(12),
		vemail varchar(40),company varchar(15),address varchar(100));


create table tender(tid varchar(15) primary key,tname varchar(40),ttype varchar(20),tprice int,
		    tdesc varchar(300),tdeadline date,tloc varchar(70));

create table bidder (bid varchar(15) primary key,vid varchar(15) references vendor(vid),tid varchar(15) references tender(tid),
		bidamount int,deadline date,status varchar(10));


create table tenderstatus(tid varchar(15) primary key references tender(tid),bid varchar(15) references bidder(bid),
		status varchar(15) not null,vid varchar(15) references vendor(vid));

INSERT INTO tender VALUES ('T20190725022124','Vaasan Highway','maintainence',50000,'Road to Seinajoki','2025-07-19','Vaasa, Finland'),('T20190725022416','MEGA CITY CONNECTING ROAD CONTRUCTION','construction',100000,'mega city road contruction','2025-08-14','Vaasa'),('T20190725022601','VAASAN BRIDGE CONTRUCTION','construction',50000,'bridge contruction ','2025-07-28','Vaasa'),('T20190725101239','Municipality Site Development','software',1500,'We are going to start a project on Municipality development. Interested candidates are required to bid as soon as possible','2025-09-19','Vaasa'),('T20190725101322','Second hand store','software',15000,'Interested candidates are required to bid as soon as possible','2025-07-19','Vaasa');


INSERT INTO notice VALUES (2,'Vaasan Ground Repairing','Repairing work is going to be started tommorow'),(3,'VAASAN BRIDGE CONTRUCTION','ASSIGNED ENGINEER NEED TO REPORT AT THE CONSTRUCTION SITE BY TOMMOROW');

INSERT INTO vendor VALUES ('V20190725020951','prabesh','Prabesh Shrestha','07501 070485','prabesh@gmail.com','systech','K-3, Vaasa'),('V20190725022813','roshan','Roshan Karki','12345679','roshan@gmail.com','systech','Helsinki'),('V20190725023446','Anni','Anni Karlsson','6789054321','anni@gmail.com','systech','seinajoki '),('V20190725100730','sara','Sara Vahtera','9234567689','sara@gmail.com','systech','Helsinki ');

INSERT INTO bidder VALUES ('B20190725022953','V20190725022813','T20190725022124',51000,'2025-07-19','Pending'),('B20190725023010','V20190725022813','T20190725022124',52000,'2025-07-19','Accepted'),('B20190725023248','V20190725022813','T20190725022416',100001,'2025-09-14','Rejected'),('B20190725023512','V20190725023446','T20190725022416',200000,'2025-09-14','Accepted'),('B20190725024125','V20190725023446','T20190725022601',5000001,'2025-09-28','Rejected'),('B20190725024243','V20190725022813','T20190725022601',6000000,'2025-09-28','Accepted'),('B20190725101444','V20190725100730','T20190725101322',1500000,'2025-09-19','Rejected'),('B20190725101519','V20190725023446','T20190725101239',150005,'2025-09-19','Rejected'),('B20190725101525','V20190725023446','T20190725101239',150050,'2025-09-19','Rejected'),('B20190725101554','V20190725022813','T20190725101322',160000,'2025-09-19','Accepted');

INSERT INTO tenderstatus VALUES ('T20190725022124','B20190725023010','Assigned','V20190725022813'),('T20190725022416','B20190725023512','Assigned','V20190725023446'),('T20190725022601','B20190725024243','Assigned','V20190725022813'),('T20190725101322','B20190725101554','Assigned','V20190725022813');

commit;
