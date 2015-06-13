package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kursat on 12/21/14.
 */
public class Sorgu extends Activity {
    public List<String> konu2;
    public String derss = "Ders Seçiniz", konus = "Konu Seçiniz", alann = "Alan Seçiniz",bolumm = "EA";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorgu);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String gelen = b.get("email").toString();
        final String gelentip = b.get("tip").toString();
        //LYS veya YGS gelir sinav.java dan

        final Database d = new Database(this);


        Cursor adam = d.ogrAl(gelen);
        adam.moveToFirst();
        bolumm = adam.getString(adam.getColumnIndex("bolum"));


        final Button btn = (Button) findViewById(R.id.button5);


        final Spinner sp1 = (Spinner) findViewById(R.id.spinner4);
        final Spinner sp2 = (Spinner) findViewById(R.id.spinner5);
        final Spinner sp3 = (Spinner) findViewById(R.id.alanspin);


        ArrayAdapter<String> adapter2;


        final List<String> alans = new ArrayList<String>();
        alans.add(0, "Alan Seçiniz");
        alans.add(1, "YGS");
        alans.add(2, "LYS");

        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, alans);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter2);

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (!sp3.getSelectedItem().equals("Alan Seçiniz")) {
                    List<String> ders = null;
                    List<String> konu = null;
                    ArrayAdapter<String> adapter;
                    ArrayAdapter<String> adapter1;
                    alann = sp3.getSelectedItem().toString();
                    if (alann.equals("YGS")) {
                        ders = d.dersAl("select * from ders where tip='YGS'");
                        konu = d.dersAl("select * from konu where sinav='YGS'");
                    } else {
                        ders = d.dersAl("select * from ders where tip='LYS' AND alan='"+bolumm+"'");
                        konu = d.dersAl("select * from konu where sinav='LYS'");
                    }
                    ders.add(0, "Ders Seçiniz");
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ders);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp2.setAdapter(adapter);
                    konu.add(0, "Konu Seçiniz");
                    adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, konu);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp1.setAdapter(adapter1);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (!sp1.getSelectedItem().equals("Konu Seçiniz")) {
                    sp2.setEnabled(false);
                    konus = sp1.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (!sp2.getSelectedItem().equals("Ders Seçiniz")) {
                    sp1.setEnabled(false);
                    derss = sp2.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("konusu - > ", konus);

                Log.e("dersi - > ", derss);

                Log.e("bolumi -> ",bolumm);

                Intent in = new Intent(Sorgu.this, Sonuc.class);
                in.putExtra("email", gelen);
                in.putExtra("konu", konus);
                in.putExtra("ders", derss);
                in.putExtra("tip", alann);
                Sorgu.this.startActivity(in);
            }
        });

    }
}