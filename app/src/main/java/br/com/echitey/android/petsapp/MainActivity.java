package br.com.echitey.android.petsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.echitey.android.petsapp.data.PetContract;
import br.com.echitey.android.petsapp.data.PetDbHelper;

public class MainActivity extends AppCompatActivity {

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

        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu options using the menu_main file
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_insert_dummy_data:
                //do nothing for now
                return true;
            case R.id.action_delete_all_entries:
                //do nothing for now
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayDatabaseInfo(){
        PetDbHelper helper = new PetDbHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ PetContract.PetEntry.TABLE_NAME, null);

        try{
            TextView displayView = findViewById(R.id.text_view_pet);
            displayView.setText("Number of Pets id Database: "+cursor.getCount());
        } finally {
            cursor.close();
        }

    }
}
