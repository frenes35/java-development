
SELECT ProductID, ProductName, UnitPrice
FROM Products;


SELECT ProductID, ProductName, UnitPrice
FROM Products
ORDER BY UnitPrice ASC;

SELECT *
FROM Products
WHERE UnitPrice <= 7.50;

SELECT *
FROM Products
WHERE UnitsInStock >= 100
ORDER BY UnitPrice DESC;

SELECT *
FROM Products
WHERE UnitsInStock >= 100
ORDER BY UnitPrice DESC, ProductName ASC;

SELECT *
FROM Products
WHERE UnitsInStock = 0 AND UnitsOnOrder > 0
ORDER BY ProductName;


SELECT *
FROM Categories;

SELECT *
FROM Products
WHERE CategoryID = 8;

SELECT FirstName, LastName
FROM Employees;

SELECT *
FROM Employees
WHERE Title LIKE '%Manager%';

SELECT DISTINCT Title
FROM Employees;

SELECT *
FROM Employees
WHERE Salary BETWEEN 2000 AND 2500;

SELECT *
FROM Suppliers;

SELECT *
FROM Products
WHERE SupplierID = 4;