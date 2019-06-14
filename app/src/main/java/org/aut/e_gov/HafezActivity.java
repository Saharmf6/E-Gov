package org.aut.e_gov;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.aut.e_gov.models.DatabaseAccess;

import java.util.Random;

public class HafezActivity extends AppCompatActivity {

    TextView txtHafez;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hafez);
        txtHafez = findViewById(R.id.txtHafez);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);


        Cursor hafezCursor = dbAccess.getDb().rawQuery("SELECT * FROM Hafez", null);
        Random rand = new Random();
        int random = (int) (hafezCursor.getCount() * rand.nextFloat());
        hafezCursor.moveToFirst();
        int i = 0;
        while (!hafezCursor.isAfterLast()){
            if (i == random){
                break;
            }
            i++;
            hafezCursor.moveToNext();
        }
        txtHafez.setText(hafezCursor.getString(0));
        txtHafez.setMovementMethod(new ScrollingMovementMethod());

    }



}
