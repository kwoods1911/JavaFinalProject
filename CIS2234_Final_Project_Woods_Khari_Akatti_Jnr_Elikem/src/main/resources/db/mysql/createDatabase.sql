DROP DATABASE if exists cis2232_flowers;
CREATE DATABASE cis2232_flowers;
USE cis2232_flowers;

CREATE TABLE ItemType (
  id int(5) NOT NULL,
  description varchar(100) NOT NULL,
  cost double NOT NULL
);

INSERT INTO itemtype (id, description, cost) 
VALUES (1, 'Welcome Baby Boy', 90), 
(2, 'Welcome Baby Girl', 85),
(3, 'Very Special Delivery', 100),
(4, 'Small Stuffed Animal', 10),
(5, 'Medium Stuffed Animal', 20),
(6, 'Large Stuffed Animal', 30);

CREATE TABLE CustomerType (
  id int(5) NOT NULL,
  description varchar(20) NOT NULL
);

CREATE TABLE OrderStatusType (
  id int(5) NOT NULL,
  description varchar(20) NOT NULL
);


CREATE TABLE Customer (
  id int(5) NOT NULL,
  customerTypeId int(5) COMMENT 'Customer type id',
  fullName varchar(100) NOT NULL COMMENT 'Customer Name',
  address1 varchar(100) NOT NULL COMMENT 'Address',
  city varchar(100)  NOT NULL COMMENT 'City',
  province varchar(20)  NOT NULL COMMENT 'Province',
  postalCode varchar(7) NOT NULL COMMENT 'Postal Code',
  phoneNumber varchar(12) NOT NULL COMMENT 'Customer Phone Number',
  birthDate varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
  loyaltyCard VARCHAR (6) DEFAULT NULL);

CREATE TABLE FlowerOrder (id INT(5),
customerId INT(5) COMMENT 'Reference to Customer id',
orderDate varchar(10) DEFAULT NULL COMMENT 'yyyy-MM-dd',
item1 INT COMMENT 'Number of item 1',
item2 INT COMMENT 'Number of item 2',
item3 INT COMMENT 'Number of item 3',
item4 INT COMMENT 'Number of item 4',
item5 INT COMMENT 'Number of item 5',
item6 INT COMMENT 'Number of item 6',
orderStatus INT COMMENT 'Order Status Type',
totalCost DECIMAL(10, 2),
amountPaid DECIMAL(10,2));

ALTER TABLE FlowerOrder
  ADD PRIMARY KEY (id);

ALTER TABLE FlowerOrder
  MODIFY id int(5) NOT NULL AUTO_INCREMENT;
COMMIT;

ALTER TABLE Customer
  ADD PRIMARY KEY (id);

ALTER TABLE Customer
  MODIFY id int(5) NOT NULL AUTO_INCREMENT;
COMMIT;

ALTER TABLE ItemType
  ADD PRIMARY KEY (id);

ALTER TABLE CustomerType
  ADD PRIMARY KEY (id);

ALTER TABLE CustomerType
  MODIFY id int(5) NOT NULL AUTO_INCREMENT;
COMMIT;

INSERT INTO CustomerType values (0, 'Regular');
INSERT INTO CustomerType values (0, 'Preferred');

ALTER TABLE OrderStatusType
  ADD PRIMARY KEY (id);

ALTER TABLE OrderStatusType
  MODIFY id int(5) NOT NULL AUTO_INCREMENT;
COMMIT;

INSERT INTO OrderStatusType values (0, 'In Progress');
INSERT INTO OrderStatusType values (0, 'Completed');

INSERT INTO Customer (id, customerTypeId, fullName, address1, city, province, postalCode, phoneNumber, birthDate, loyaltyCard) VALUES
(0, 1, 'Cameron MacDonald', '77 Elm Lane', 'Charlottetown', 'PE', 'c1a 1t5', '666-666-6666', '2001-03-04', null)
,(0, 2, 'Karen George', '66 Park Lane', 'Charlottetown', 'PE', 'c1a 3l4', '902-314-1234', '1999-12-25','123321')
,(0, 2, 'Parker Gallant', '123 South Drive', 'Summerside', 'PE', 'c1c 3x4', '902-436-7676', '1999-12-01','222334');


INSERT INTO flowerorder (id, customerId, orderDate, item1, item2, item3, item4, item5, 
item6, orderStatus, totalCost, amountPaid) 
VALUES ('0', '2', '2020-10-09', '0', '0', '2', '0', '0', '0', '1', '200', '0');