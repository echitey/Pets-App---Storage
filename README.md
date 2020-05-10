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
-   Querying database and navigating through the cursor
-   Create a Content Provider that will be used to interact with the DB
-   Create a Cursor Loader that will be used to retrieve the date using the Content Provider and allow live update
-   Create the Provider Class
-   Define the URI Matcher pattern
-   Create DB Operation + Validation methods in Provider
-   Create CursorAdapter and ListView Layout
-   Implementing a CursorLoader(uses background thread, fit ContentProvider, automatic refresh)
