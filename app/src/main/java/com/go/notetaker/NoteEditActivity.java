package com.go.notetaker;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        final TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        final Button saveButton = (Button) findViewById(R.id.saveButton);

        // Iratkozzunk fel a saveButton onClick eseményfigyelőjére.
        // Figyelj az importokra! Arra az esetre, ha elbizonytalanodnál:
        // https://developer.android.com/reference/packages.html (kereső)
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                // Teszetelésnél keresd ezt az üzenetet a logcat-ben.
                // Akár filtert is használhatsz rá, úgy még gyorsabb.
                Log.d("click-event", "it's clicked");

                if (mIsEditMode) {
                    // billentsük át, hiszen módot váltunk
                    mIsEditMode = false;

                    // setEnabled false értéke mondja azt, hogy tiltsa le a vezérlőt
                    titleEditText.setEnabled(false);
                    dateTextView.setEnabled(false);

                    // a mentési idő megjelenítése több lépésből áll
                    // kell először egy formátum, majd az aktuális idő,
                    // majd az előbbivel formázom az utóbbit, és már
                    // csak be kell állítani a szövegnek
                    final DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                    Date now = new Date();
                    final CharSequence timeText = timeFormat.format(now);
                    dateTextView.setText(timeText);

                    // módot váltottunk, ugye ezt a gomb szövegében is érdemes jelezni
                    saveButton.setText("Edit");
                } else {
                    // lényegében ezen az ágon az előző ág fordítottja megy végbe
                    mIsEditMode = true;

                    titleEditText.setEnabled(true);
                    dateTextView.setEnabled(true);

                    // resourceből betölteni sokkal szebb megoldás
                    dateTextView.setText(R.string.textview_date_empty);

                    saveButton.setText("Save");
                }
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
