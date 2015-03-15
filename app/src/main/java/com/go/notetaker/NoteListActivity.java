package com.go.notetaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NoteListActivity extends ActionBarActivity {

    private ArrayList<Note> mNotes;
    private List<String> mValues;

    // a másik activity visszatérési értékét itt fogadjuk
    @Override protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {
            // lekérem a NoteEditActivity-ben vissazaadott Note objektumot a data paraméterből,
            // ami, ha megfigyeled egy Intent példány
            final Note note = (Note) data.getSerializableExtra("note");

            // eltárolod azt
            mNotes.add(note);
            mValues.add(note.getTitle());

            // és végül megjeleníted
            populateList();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // Már nem stringeket akarunk belepakolni, valódi Note objektumokkal,
        // szeretnénk dolgozni.
        mNotes = new ArrayList<>();
        mNotes.add(new Note("First note", "Note 1", new Date()));
        mNotes.add(new Note("Second note", "Note 2", new Date()));
        mNotes.add(new Note("Third note", "Note 3", new Date()));

        // Ebből következőleg a values is változik, töröltük az összes .add hívást
        mValues = new ArrayList<>();
        // de mivel az arrayadapter használja a values, így kényelmesebb megtartani,
        // és a noteokból betölteni az elemeket.
        for (Note note : mNotes) {
            mValues.add(note.getTitle());
        }
        populateList();
    }

    private void populateList() {
        // Szükségünk van a ListView-hoz egy adapterre, kezdésnek használjuk ezt az egyszerű megoldást,
        // az android.R. azt jelenti be van építve az Android rendszerbe, de az is sima resource,
        // control + click akár a simple_list_item_1-re, akár a text1-re, és jó lesz.
        final ListView noteList = (ListView) findViewById(R.id.noteListView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mValues);
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
            // át akarok navigálni a másik activity-re, hogy ezt megtehessem,
            // létrehozok egy intent-et, amiben leírom a kiindulási pontot,
            // azaz a jelen activityt (ezt jelenti a this), és a célt is
            Intent intent = new Intent(this, NoteEditActivity.class);
            // úgy kell elindítani az Activity-t, hogy visszatérési értéket várok
            // a második paraméter, a requestCode értéke mindegy, csak egyedi legyen
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}