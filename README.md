# AssemQueryTool

AssemQueryTool is a quick helping debug tool you would need when deals with SQL, especially when you using Hibernate (HQL), which all properties of table kinda like:

- (SQL) dept_ID -> (Hibernate) deptID
- (SQL) user_name_config -> (Hibernate) userNameConfig

# Features

1. Helping re-structure, format SQL (HQL) make look more readable. For example, the querry 
   " select * from users where userName = 'John' "
   would be formating to: 
   ```sql
   SELECT

  *

2. Re-assemble parameter to querry (**Work with Intellij only**): When you deal with Hibernate or sql, you would get some things like this when debugging:
- HQL: 
  
  ```sql
  select * from Category c   where c.isActive = 1 and c.categoryTypeCode=?  AND (c.deptId  IS NULL OR c.deptId = ? OR c.deptId IN (:listParents)) order by   NLSSORT(c.name,'NLS_SORT=vietnamese')
  ```
- Params (from Intellij version 2020): 
  
  ```sql
  0 = "CATEGORY_FIELD"
  1 = {Long@9281} 6
  ```

==> And output would below

```sql
SELECT
  *
FROM
  Category c
WHERE
  c.is_Active = 1
  AND c.category_Type_Code = 'CATEGORY_FIELD'
  AND (
    c.dept_Id IS NULL
    OR c.dept_Id = 6
    OR c.dept_Id IN (:list_Parents)
  )
ORDER BY
  NLSSORT(c.name, 'NLS_SORT=vietnamese');
```

3. Turn **camelCase** to **camel_Case** for SQL rules. 
   ***Warning: This feature could lead to some miss understanding when your Database is camelCase type***

# Build

1. Using java version >= 8
2. My project is base on Netbean 8.2 file structure. 
3. You would need *sql-formatter-1.0.1.jar* which can download belown:   
   https://github.com/vertical-blank/sql-formatter
