SELECT * FROM dealerships;

SELECT v.* FROM vehicles v
JOIN inventory i ON v.vehicle_id = i.vehicle_id
WHERE i.dealership_id = 1;

SELECT * FROM vehicles WHERE VIN = '1HGBH41JXMN109186';

SELECT d.* FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.vehicle_id = v.vehicle_id
WHERE v.VIN = '1HGBH41JXMN109186';

SELECT d.* FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.vehicle_id = v.vehicle_id
WHERE v.color = 'Red' AND v.make = 'Ford' AND v.model = 'Mustang';

SELECT s.* FROM sales_contracts s
JOIN inventory i ON s.vehicle_id = i.vehicle_id
WHERE i.dealership_id = 1 AND s.sale_date BETWEEN '2023-07-01' AND '2023-07-31';