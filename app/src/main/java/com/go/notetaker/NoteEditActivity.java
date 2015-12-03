package com.go.notetaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;


public class NoteEditActivity extends ActionBarActivity {

    // Így tároljuk, hogy adott esetben Save vagy Edit mode-ban vagyunk.
    private boolean mIsEditMode = true;

    // Az activity indításakor ez a metódus mindig lefut.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Betöltésre kerül a activity_note_edit.xml a view-ba.
        // Figyeld meg, a név ugyanaz, viszont a .xml kiterjesztés nem kell.
        setContentView(R.layout.activity_note_edit);

        // Összekötjük az előbb betöltött layout elemeit a Java file-al,
        // hogy azokat programozásra is használhassuk.
        // Figyelj az importokra!
        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        // hoppá, a következő korábban kimaradt, de most már tényleg szükség van rá
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);
        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        final Button saveButton = (Button) findViewById(R.id.saveButton);

        // Iratkozzunk fel a saveButton onClick eseményfigyelőjére.
        // Figyelj az importokra! Arra az esetre, ha elbizonytalanodnál:
        // https://developer.android.com/reference/packages.html (kereső)
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                // célunk, hogy a Save gomb lenyomására visszaküldjük a beírt jegyzetet a listának
                // hogy ezt megtehessük, kiolvassuk a beviteli mezőkből a beírt címet és jegyzetet
                String titleText = titleEditText.getText().toString();
                String noteText = noteEditText.getText().toString();

                // az aktuális időt
                Date dateTime = new Date();

                // létrehozunk egy új note objektumot
                Note note = new Note(titleText, noteText, dateTime);

                // kell egy intent, amihez az elkészült note-ot csatoljuk mint plusz adatot
                // most nem kell paraméterezni, most csak visszaadjuk az elkészült noteot
                Intent returnIntent = new Intent();
                // emlkézzetek, csak elemi adatokat lehet átadni, vagy Serializable-t,
                // tegyük a Note osztályt ezzé, és jó lesz
                returnIntent.putExtra("note", note);
                returnIntent.putExtra("note2", note);
                returnIntent.putExtra("note3", note);
                // állítsuk be a visszatérési értékeket, RESULT_OK jelenti azt, hogy
                // minden rendben, valamint visszaadunk az elkészült returnIntent-et is
                setResult(RESULT_OK, returnIntent);
                // ténylegesen visszatérünk
                finish();
            }
        });
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
