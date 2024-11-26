---- Question 1: Add a new supplier

INSERT INTO Suppliers (SupplierID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone)
VALUES (30, 'New Supplier', 'John Doe', 'CEO', '123 Elm St', 'Sample City', 'Region A', '12345', 'Country X', '123-456-7890');
;


INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued)
VALUES (78,'New Product',30,1,10.00);


SELECT Products.ProductName, Suppliers.CompanyName
FROM Products
LEFT JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID;

UPDATE Products
SET UnitPrice = UnitPrice * 1.15
WHERE ProductID = '78';

--- Question 5: List the products and prices of all products from that supplier
SELECT ProductName,UnitPrice
FROM Products
WHERE SupplierID = 30;

DELETE FROM Products
WHERE ProductID = 30;

DELETE FROM Suppliers
WHERE CompanyName = 'New Supplier Co';

SELECT * FROM Products;

SELECT * FROM Suppliers;