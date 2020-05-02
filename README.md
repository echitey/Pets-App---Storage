# Pets App from Udacity Storage Course

## Process
-   Identify Schema (Table name, column names, data types)
-   Create a Contract class
A Contract class define the schema with all the structure and the constants of our databases
Helps avoid spelling errors when generating sql commands
Ease of updating database schema
-   Create the SQLiteOpenHelper class
It creates the db when it is first accessed
It gives a connection to the DB
Manages updating the database schema if version changes