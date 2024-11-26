

SELECT COUNT(*) AS SupplierCount
FROM Suppliers;


SELECT SUM(Salary) AS TotalSalary
FROM suppliers;


SELECT MIN(UnitPrice) AS CheapestPrice
FROM Products;


SELECT AVG(UnitPrice) AS AveragePrice
FROM Products;


SELECT MAX(UnitPrice) AS MostExpensivePrice
FROM Products;



SELECT SupplierID, COUNT(*) AS ItemCount
FROM products
GROUP BY SupplierID;


SELECT CategoryID, AVG(UnitPrice) AS AveragePrice
FROM products
GROUP BY CategoryID;


SELECT SupplierID, COUNT(*) AS ItemCount
FROM products
GROUP BY SupplierID
HAVING ItemCount >= 5;



SELECT ProductID,ProductName,UnitPrice * UnitInStock As InventoryValue
FROM products
ORDER BY InventoryValue DESC , ProductName ASC;

