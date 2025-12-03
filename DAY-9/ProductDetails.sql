/*Product details
The e-commerce offering online services to their clients. They are managing the product's sale and purchase 
through this website. They wants find the products with different categories like furniture,laptop and mobile */

-- Write your query here
SELECT *
FROM product
WHERE category IN ('furniture', 'laptop', 'mobile')
  AND price < 50000;