

SELECT ProductName
FROM products
WHERE UnitPrice = (SELECT MAX(UnitPrice)
FROM products);





SELECT Orders.OrderID, Orders.ShipName, Orders.ShipAdress
FROM Orders
WHERE Orders.ShipVia = (
SELECT ShipperID
FROM Shippers
WHERE CompanyName = 'Federal Shipping'
);



SELECT OrderID
FROM `Order Details`
WHERE ProductID = (
  SELECT ProductID
  FROM Products
  WHERE ProductName = 'Sasquatch Ale'
);

SELECT FirstName, LastName
FROM Employees
WHERE EmployeeID = (
  SELECT EmployeeID
  FROM Orders
  WHERE OrderID = 10266
);

SELECT CompanyName
FROM Customers
WHERE CustomerID = (
  SELECT CustomerID
  FROM Orders
  WHERE OrderID = 10266
);