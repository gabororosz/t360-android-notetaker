package com.go.notetaker;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NoteEditActivity extends ActionBarActivity {

    // Az activity indításakor ez a metódus mindig lefut.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Betöltésre kerül a activity_note_edit.xml a view-ba.
        // Figyeld meg, a név ugyanaz, viszont a .xml kiterjesztés nem kell.
        setContentView(R.layout.activity_note_edit);

        // Összekötjük az előbb betöltött layout elemeit a Java file-al,
        // hogy azokat programozásra is használhassuk.
        // Figyelj az importokra.
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        final Button saveButton = (Button) findViewById(R.id.saveButton);
    }


    // Betöltésre kerül a menu_note_edit.xml a a felső sávba, az ún. ActionBar-ba.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_edit, menu);
        return true;
    }

    // Erre a metódusra egyelőre nincs szükség, de később lesz.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
