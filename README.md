## Super Duper Maven Project

##Requirements :
* Java 17
* Intellij IDE
* MySQL 8.0 Command Line Client // if you want to test with sql data

##HOW TO RUN
Open Project in Intellij as Maven Projct
Go to Main.java
Run Main

Main Has 3 Section Seperated with Commented Instruction :
Each Section is for a different source of DATA (default is hardcoded data)
Section 1 : Uncomment the first Section to load hardCoded data defined in /utils/SampleDataLoader.java
Section 2 : Uncomment the second section to load data from a csv file which is placed in /resources/products.csv
Section 3 : Uncomment the third section to load data from an Sql Database

The Simulator will will print out initial data values, from the chosen datasaource,
and iteratively print out their values after implementing updates depending on the chosen number of days to Simulate

The choice of the number of days to be simulated can be chosen in Main.java on the last line as shown here:
      //  simulator.run(LocalDate.now(), NumberOfDays);   <-----

##Adding a new Product
1- Add model
2- Add new product updater
3- register new product in ProductService Constructor // already created 2 new models : Honey and Joghurt


##SQL - Instruction on SQL setup , commands ready for copy paste :
##Create superuser with priviliges
```
DROP USER IF EXISTS 'superuser'@'localhost';
CREATE USER 'superuser'@'localhost' IDENTIFIED BY 'SuperDuper123!';
GRANT ALL PRIVILEGES ON superduper.* TO 'superuser'@'localhost';
FLUSH PRIVILEGES;
```
##Create database
```
CREATE DATABASE superduper
```
##USE superduper database
```
USE superduper;
```
##In case of issue you might need :
```
DROP TABLE IF EXISTS products;
```
##Create Table
```
CREATE TABLE products (
      id INT AUTO_INCREMENT PRIMARY KEY,
      type VARCHAR(50) NOT NULL,       -- Cheese, Joghurt, Wine, Honey
      name VARCHAR(100) NOT NULL,
      quality INT NOT NULL,
      base_price DOUBLE NOT NULL,
      expiry_date DATE DEFAULT NULL,    -- only for Cheese and Joghurt
      stock_date DATE DEFAULT NULL      -- only for Wine
 );
```
##Insert Data into table
```
INSERT INTO products (type, name, quality, base_price, expiry_date, stock_date) VALUES
     ('Cheese', 'Gouda', 50, 10.0, '2025-12-28', NULL),
     ('Cheese', 'Brie', 40, 12.0, '2025-12-18', NULL),
     ('Joghurt', 'Joghurt', 7, 15.0, '2025-12-30', NULL),
     ('Wine', 'Merlot', 20, 30.0, NULL, '2025-01-20'),
     ('Wine', 'Chardonnay', 10, 25.0, NULL, '2025-01-15'),
     ('Honey', 'Acacia', 50, 20.0, NULL, NULL);
```
##Log Data in SQL command line
```
SELECT * FROM products;
```