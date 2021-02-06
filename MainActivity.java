package com.example.gestion_employes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Operation operation;
    Button addEmp, delEmp, cheEmp,locEmp;



    //Apres de Remplire les champs de add on a vider les champs
    // Update
    //modification de plus


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GESTION_EMPLOYES");
        db = new DatabaseHelper(this);
        chargerListEmp();
        // chargerList  apres impemante fonction

        addEmp = findViewById(R.id.buttonAdd);
        addEmp.setOnClickListener(view -> {

            SQLiteDatabase dbemp = db.getWritableDatabase();

            Cursor res = dbemp.rawQuery("SELECT MAX(ID) From " + DatabaseHelper.TABLE_NAME , null);

            int id_new_Emp=0;
            if (res.moveToNext()) {
                 id_new_Emp = res.getInt(0);
            }
            Intent intent_Chercher = new Intent(getApplicationContext(), Add_Employe.class);
            intent_Chercher.putExtra("ID", id_new_Emp+1);
            startActivity(intent_Chercher);


        });

// Methode Delete
        delEmp = (Button) findViewById(R.id.buttonDelete);
        delEmp.setOnClickListener(view ->{
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.Theme_AppCompat_Dialog);
            alert.setTitle("Delete");
            alert.setMessage("Tape ID of Employed: ");

            final EditText input = new EditText(getApplicationContext());
            alert.setView(input);

            alert.setPositiveButton("OK",(dialog, which) -> {
                String _id = input.getText().toString();
                int id = Delete(_id);
                if (id > 0  ){
                    Toast.makeText(MainActivity.this,"Suppression effectue avec succés: ",Toast.LENGTH_SHORT).show();
                    chargerListEmp();
                }
                else {
                    Toast.makeText(MainActivity.this,"Employes"+_id+" n'exite pas !!! ",Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("Cancel",(dialog, which) -> {

            });
            alert.show();
        });



        //Methode de chercher ...
        cheEmp = findViewById(R.id.buttonCherche);
        cheEmp.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog);
            alert.setTitle("Rechercher");
            alert.setTitle("Tapez your code");

            final EditText input = new EditText(getApplicationContext());
            alert.setView(input);
            alert.setPositiveButton("OK", (dialog, which) -> {
                int _ID = Integer.parseInt(input.getText().toString());
                System.out.println(_ID);
                Employe emp = getEmplByID(_ID);
                if (emp != null) {
                    Intent intent_Chercher = new Intent(getApplicationContext(), RechercheEmploye.class);
                    intent_Chercher.putExtra("ID", emp.getId());
                    intent_Chercher.putExtra("nom", emp.getNom());
                    intent_Chercher.putExtra("prenom", emp.getPrenom());
                    intent_Chercher.putExtra("tel", emp.getTel());
                    intent_Chercher.putExtra("mission", emp.getMission());
                    intent_Chercher.putExtra("date_dep", emp.getDate_depart());
                    intent_Chercher.putExtra("date_arr", emp.getDate_arrive());
                    startActivity(intent_Chercher);
                } else {
                    Toast.makeText(MainActivity.this, "Employed of " + _ID + " is not existed", Toast.LENGTH_SHORT).show();
                }

            });
            alert.setNegativeButton("Cancel", (dialog, which) -> {

            });
            alert.show();
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        chargerListEmp();
    }

    public void chargerListEmp() {

        ArrayList<String> items = new ArrayList<>();
        for (Employe emp : getListEmployes())
            items.add(emp.getId() + " | " + emp.getNom() + " | " + emp.getPrenom() + " | " + emp.getTel() + " | " +
                    emp.getMission() + " | " + emp.getDate_depart() + " | " + emp.getDate_arrive());

        ArrayAdapter<String> itemAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(itemAdp);
    }


    //Les Operations Secondaire

    public ArrayList<Employe> getListEmployes() {
        SQLiteDatabase dbEmp = db.getWritableDatabase();
        Cursor res = dbEmp.rawQuery("Select * From " + DatabaseHelper.TABLE_NAME, null);
        ArrayList<Employe> listemp = new ArrayList<>();
        while (res.moveToNext()) {
            Employe emp = new Employe(res.getInt(0), res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6));
            listemp.add(emp);
        }
        return listemp;
    }

    public Employe getEmplByID(int id) {
        SQLiteDatabase dbemp = db.getWritableDatabase();
        Cursor res = dbemp.rawQuery("Select * From " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_id + "= " + id, null);
        if (res.moveToNext()) {
            Employe emp = new Employe(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
            return emp;
        } else {
            return null;
        }
    }

    public Integer Delete(String id) {
        if (id != null) {
            SQLiteDatabase dbEmpoyes = db.getWritableDatabase();
            return dbEmpoyes.delete(DatabaseHelper.TABLE_NAME, "ID = ? ", new String[]{id});
        } else {
            return 0;
        }

    }



}
