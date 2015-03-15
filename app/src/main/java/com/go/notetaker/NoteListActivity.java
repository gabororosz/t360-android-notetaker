package com.go.notetaker;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NoteListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        final ListView noteList = (ListView) findViewById(R.id.noteListView);

        // Már nem stringeket akarunk belepakolni, valódi Note objektumokkal,
        // szeretnénk dolgozni.
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("First note", "Note 1", new Date()));
        notes.add(new Note("Second note", "Note 2", new Date()));
        notes.add(new Note("Third note", "Note 3", new Date()));

        // Ebből következőleg a values is változik, töröltük az összes .add hívást
        List<String> values = new ArrayList<>();
        // de mivel az arrayadapter használja a values, így kényelmesebb megtartani,
        // és a noteokból betölteni az elemeket.
        for (Note note : notes) {
            values.add(note.getTitle());
        }

        // Szükségünk van a ListView-hoz egy adapterre, kezdésnek használjuk ezt az egyszerű megoldást,
        // az android.R. azt jelenti be van építve az Android rendszerbe, de az is sima resource,
        // control + click akár a simple_list_item_1-re, akár a text1-re, és jó lesz.
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        noteList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_note) {
            Log.d("actionbar", "add note clicked");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}