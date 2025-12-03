/*Employeement Salary Analysis
 A multinational company is conducting an analysis of employee performance to identify top-performing 
 individuals within each department. Your task is to retrieve the names of employees who earn a higher 
 salary than the average salary in their respective departments. Write an SQL query to extract the names 
 of employees who earn a salary higher than the average salary in their respective departments. The result 
 should include the first name, last name, department name, and salary of these top-performing employees in 
 ascending order according to the salary. */

-- Write your query below
-- Retrieve employees who earn more than the average salary in their department
SELECT 
    e.FirstName,
    e.LastName,
    d.DepartmentName,
    e.Salary
FROM 
    Employees e
JOIN 
    Departments d
ON 
    e.DepartmentID = d.DepartmentID
WHERE 
    e.Salary > (
        SELECT 
            AVG(Salary)
        FROM 
            Employees
        WHERE 
            DepartmentID = e.DepartmentID
    )
ORDER BY 
    e.Salary ASC;