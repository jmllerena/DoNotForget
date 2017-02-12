package com.jmllerena.donotforget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jmllerena.donotforget.dao.Note;

import java.util.Date;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity {

    /**
     * TAG to use with logs
     */
    private static final String TAG = "ListNotesActivity";

    ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate begin function");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        // Initialization toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize ListView
        lvNotes = (ListView) findViewById(R.id.lvNotes);

        // Create floating button and add an action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(newNoteButton);
        Log.i(TAG, "onCreate end function");

        // Load notes in ListView
        loadNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_notes, menu);
        return true;
    }

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

    /**
     * Variable to control new note button action
     */
    private FloatingActionButton.OnClickListener newNoteButton = new FloatingActionButton.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Snackbar.make(view, "Que guapo eres cojones", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();

            // Creamos un dialogo para preguntar el tipo de Nota que desea crear
            AlertDialog.Builder builder = new AlertDialog.Builder(ListNotesActivity.this);
            builder.setTitle("New Note")
                    .setItems(R.array.notes_types, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Note note = null;
                            switch (which) {
                                case 0:
                                    note = new Note("No description", new Date(), Note.TypeNote.BLOC);
                                    note.save();
                                    break;
                                case 1:
                                    note = new Note("No description", new Date(), Note.TypeNote.LIST);
                                    note.save();
                                    break;
                            }
                            // Load the listview with the new note
                            if (note != null)
                                loadNotes();

                            // TODO: open the BLOC/LIST editor after create
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    /**
     * Load Notes in the list view
     */
    private void loadNotes() {
        // Instance the ListView object
        ListView lvNotes;
        lvNotes = (ListView) findViewById(R.id.lvNotes);

        // Query for Notes
        List<Note> notes = Note.listAll(Note.class);

        // Load listview with query using adapter
        lvNotes.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes));
    }
}
