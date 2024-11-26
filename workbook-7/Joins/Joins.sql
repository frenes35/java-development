SELECT 
    Products.ProductID, Products.ProductName, Products.UnitPrice, Categories.CategoryName
FROM Products
JOIN Categories ON Products.CategoryID = Categories.CategoryID
ORDER BY 
    Categories.CategoryName, 
    Products.ProductName;

SELECT 
    Products.ProductID, Products.ProductName, Products.UnitPrice, Suppliers.CompanyName AS SupplierName
FROM Products
JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID
WHERE 
    Products.UnitPrice > 75
ORDER BY 
    Products.ProductName;

SELECT 
    Products.ProductID, Products.ProductName, Products.UnitPrice, Categories.CategoryName, Suppliers.CompanyName AS SupplierName
FROM Products
JOIN Categories ON Products.CategoryID = Categories.CategoryID
JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID
ORDER BY 
    Products.ProductName;

SELECT p.ProductName, c.CategoryName
FROM Products p
INNER JOIN Categories c ON p.CategoryID = c.CategoryID
WHERE p.UnitPrice = (
  SELECT MAX(UnitPrice)
  FROM Products
);

SELECT o.OrderID, o.ShipName, o.ShipAddress, s.CompanyName AS ShippingCompany
FROM Orders o
INNER JOIN Shippers s ON o.ShipVia = s.ShipperID
WHERE o.ShipCountry = 'Germany';

SELECT o.OrderID, o.OrderDate, o.ShipName, o.ShipAddress
FROM Orders o
INNER JOIN `Order Details` od ON o.OrderID = od.OrderID
INNER JOIN Products p ON od.ProductID = p.ProductID
WHERE p.ProductName = 'Sasquatch Ale';