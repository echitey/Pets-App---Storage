package br.com.echitey.android.petsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.echitey.android.petsapp.data.PetContract;
import br.com.echitey.android.petsapp.data.PetContract.PetEntry;
import br.com.echitey.android.petsapp.data.PetDbHelper;
import br.com.echitey.android.petsapp.data.PetProvider;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private PetDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Setting up the fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        helper = new PetDbHelper(this);

        //displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu options using the menu_main file
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void seedData(){
        //Create and/or open a database to read from it
        SQLiteDatabase db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        //long newRowId = db.insert(PetEntry.TABLE_NAME, null, values);
        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_insert_dummy_data:
                //do nothing for now
                seedData();
                return true;
            case R.id.action_delete_all_entries:
                //do nothing for now
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayDatabaseInfo(){

        // Create and/or open a database to read from it
        //SQLiteDatabase db = helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT };

        // Perform a query on the pets table
        /**Cursor cursor = db.query(
                PetEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
         **/

        Cursor cursor = getContentResolver().query(
                PetContract.PetEntry.CONTENT_URI,
                projection, null, null, null);

        // Find the ListView which will be populated with the pet data
        ListView petListView = (ListView) findViewById(R.id.list);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        PetCursorAdapter adapter = new PetCursorAdapter(this, cursor);

        // Attach the adapter to the ListView.
        petListView.setAdapter(adapter);


    }
}
