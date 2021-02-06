package com.example.gestion_employes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RechercheEmploye extends AppCompatActivity {
    int click = 0;
    DatabaseHelper db;
    EditText txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_employe);
        setTitle("Recherche_Employed");
        db = new DatabaseHelper(this);
        txt1 = findViewById(R.id.textView_ID);
        txt2 = findViewById(R.id.text_Viewnomm);
        txt3 = findViewById(R.id.text_Viewprenomm);
        txt4 = findViewById(R.id.text_tellView);
        txt5 = findViewById(R.id.text_missionnView);
        txt6 = findViewById(R.id.text_deppView);
        txt7 = findViewById(R.id.text_arrrView);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
            String _ID = Integer.toString(bundle.getInt("ID"));
            String nom = bundle.getString("nom");
            String prenom = bundle.getString("prenom");
            String tel = bundle.getString("tel");
            String mission = bundle.getString("mission");
            String date_dep = bundle.getString("date_dep");
            String date_arr = bundle.getString("date_arr");
             disableEditText();

            txt1.setText(_ID);
            txt2.setText(nom);
            txt3.setText(prenom);
            txt4.setText(tel);
            txt5.setText(mission);
            txt6.setText(date_dep);
            txt7.setText(date_arr);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button btnBack = findViewById(R.id.buttonBackK);
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });


        //update

    Button btnUpdate = findViewById(R.id.buttonUpdate);
    btnUpdate.setOnClickListener(v -> {

        if(click == 0){
            enableEditText();
            Toast.makeText(this, "The input it enable now your can update your data", Toast.LENGTH_SHORT).show();
            click++;
        }
            else{
        int __id = Integer.parseInt(_ID);
            String _nom      = txt2.getText().toString();
            String _prenom   = txt3.getText().toString();
            String _tel      = txt4.getText().toString();
            String _mission  = txt5.getText().toString();
            String _date_dep = txt6.getText().toString();
            String _date_arr = txt7.getText().toString();

            SQLiteDatabase dbemp = db.getWritableDatabase();
            dbemp.execSQL(" UPDATE "+DatabaseHelper.TABLE_NAME+" SET "
                    +DatabaseHelper.COL_NOM+         " = '" + _nom      +"' , "
                    +DatabaseHelper.COL_PRENOM+      " = '" + _prenom   +"' , "
                    +DatabaseHelper.COL_TELEPHONE+   " = '" + _tel      +"' , "
                    +DatabaseHelper.COL_MISSION+     " = '" + _mission  +"' , "
                    +DatabaseHelper.COL_DATE_DEPART+ " = '" + _date_dep +"' , "
                    +DatabaseHelper.COL_DATE_ARRIVE+ " = '" + _date_arr +"' WHERE "+DatabaseHelper.COL_id+" = "+__id);

    Cursor res = dbemp.rawQuery("Select * From " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_id + " = " + __id, null);

            if (res.moveToNext()) {
                Employe emp = new Employe(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
        Toast.makeText(RechercheEmploye.this, "The employe is: "+emp.toString(), Toast.LENGTH_SHORT).show();}
            else {
        Toast.makeText(RechercheEmploye.this, "not possible", Toast.LENGTH_SHORT).show();
            }
        Toast.makeText(this, "DATA It sending !! ", Toast.LENGTH_SHORT).show();
        }}); }

    private boolean disableEditText() {

        txt1.setTextSize(18);
        txt1.setTextColor(Color.rgb(209,206,206));
        txt2.setTextSize(18);
        txt2.setTextColor(Color.BLACK);
        txt3.setTextSize(18);
        txt3.setTextColor(Color.BLACK);
        txt4.setTextSize(18);
        txt4.setTextColor(Color.BLACK);
        txt5.setTextSize(18);
        txt5.setTextColor(Color.BLACK);
        txt6.setTextSize(18);
        txt6.setTextColor(Color.BLACK);
        txt7.setTextSize(18);
        txt7.setTextColor(Color.BLACK);

        txt1.setBackgroundColor(Color.rgb(178,102,255));
        txt2.setBackgroundColor(Color.rgb(178,102,255));
        txt3.setBackgroundColor(Color.rgb(178,102,255));
        txt4.setBackgroundColor(Color.rgb(178,102,255));
        txt5.setBackgroundColor(Color.rgb(178,102,255));
        txt6.setBackgroundColor(Color.rgb(178,102,255));
        txt7.setBackgroundColor(Color.rgb(178,102,255));

        txt1.setEnabled(false);
        txt2.setEnabled(false);
        txt3.setEnabled(false);
        txt4.setEnabled(false);
        txt5.setEnabled(false);
        txt6.setEnabled(false);
        txt7.setEnabled(false);

        txt1.setFocusable(false);
        txt2.setFocusable(false);
        txt3.setFocusable(false);
        txt4.setFocusable(false);
        txt5.setFocusable(false);
        txt6.setFocusable(false);
        txt7.setFocusable(false);

        txt1.setFocusableInTouchMode(false);
        txt2.setFocusableInTouchMode(false);
        txt3.setFocusableInTouchMode(false);
        txt4.setFocusableInTouchMode(false);
        txt5.setFocusableInTouchMode(false);
        txt6.setFocusableInTouchMode(false);
        txt7.setFocusableInTouchMode(false);
;

return true;
    }

    private void enableEditText() {
            txt2.setBackgroundColor(Color.rgb(209,206,206));
            txt3.setBackgroundColor(Color.rgb(209,206,206));
            txt4.setBackgroundColor(Color.rgb(209,206,206));
            txt5.setBackgroundColor(Color.rgb(209,206,206));
            txt6.setBackgroundColor(Color.rgb(209,206,206));
            txt7.setBackgroundColor(Color.rgb(209,206,206));

            txt2.setEnabled(true);
            txt3.setEnabled(true);
            txt4.setEnabled(true);
            txt5.setEnabled(true);
            txt6.setEnabled(true);
            txt7.setEnabled(true);

            txt2.setFocusable(true);
            txt3.setFocusable(true);
            txt4.setFocusable(true);
            txt5.setFocusable(true);
            txt6.setFocusable(true);
            txt7.setFocusable(true);

            txt2.setFocusableInTouchMode(true);
            txt3.setFocusableInTouchMode(true);
            txt4.setFocusableInTouchMode(true);
            txt5.setFocusableInTouchMode(true);
            txt6.setFocusableInTouchMode(true);
            txt7.setFocusableInTouchMode(true);

            txt2.setTextSize(18);
            txt3.setTextSize(18);
            txt4.setTextSize(18);
            txt5.setTextSize(18);
            txt6.setTextSize(18);
            txt7.setTextSize(18);


        }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}
