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

import com.jmllerena.donotforget.dao.Note;

import java.util.Date;

public class ListNotesActivity extends AppCompatActivity {

    /**
     * TAG to use with logs
     */
    private static final String TAG = "ListNotesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate begin function");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        // Initialization toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create floating button and add an action
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Que guapo eres cojones", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                // Creamos un dialogo para preguntar el tipo de Nota que desea crear
                AlertDialog.Builder builder = new AlertDialog.Builder(ListNotesActivity.this);
                builder.setTitle("New Note")
                       .setItems(R.array.notes_types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Note note;
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
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        Log.i(TAG, "onCreate end function");
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
}
