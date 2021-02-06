package com.example.gestion_employes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Employe extends AppCompatActivity {
    DatabaseHelper db ;
    Operation operation;
    Button btnEnregistrer,btnBack;
    EditText txt_nom ,txt_prenom,txt_tel,txt_mission,txt_date_dep,txt_date_arr,txt_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__employe);
        setTitle("ADD_Employe");
    db= new DatabaseHelper(this);
    operation = new Operation(db);

    txt_ID = findViewById(R.id.text_ID);
    txt_nom = findViewById(R.id.text_nomm);
    txt_prenom = findViewById(R.id.text_prenomm);
    txt_tel = findViewById(R.id.text_tellView);
    txt_mission = findViewById(R.id.text_missionnView);
    txt_date_dep = findViewById(R.id.text_deppView);
    txt_date_arr = findViewById(R.id.text_arrrView);
            disableEditText();
        Bundle  bundle = new Bundle();
        bundle = getIntent().getExtras();
        String _ID = Integer.toString(bundle.getInt("ID"));
        txt_ID.setText(_ID);

    btnEnregistrer = findViewById(R.id.buttonEnrt);
    btnEnregistrer.setOnClickListener(v -> {
        String nom = txt_nom.getText().toString();
        String prenom = txt_prenom.getText().toString();
        String tel = txt_tel.getText().toString();
        String mission = txt_mission.getText().toString();
        String date_dep = txt_date_dep.getText().toString();
        String date_arr = txt_date_arr.getText().toString();

            operation.insertData(new Employe(nom, prenom, tel, mission, date_dep, date_arr));
            Toast.makeText(Add_Employe.this, "The Employed is Added ! ", Toast.LENGTH_SHORT).show();
            txt_nom .setText("");
            txt_prenom.setText("");
            txt_tel.setText("");
            txt_mission.setText("");
            txt_date_dep.setText("");
            txt_date_arr.setText("");
        });
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);});}

    private void disableEditText() {
        txt_ID.setBackgroundColor(Color.rgb(209,206,206));
        txt_ID.setEnabled(false);
        txt_ID.setFocusable(false);
        txt_ID.setFocusableInTouchMode(false);
    }


}