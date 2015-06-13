package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Ders extends Activity{
    public List<String> konu2;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ders);
        Intent i = getIntent();
        Bundle b= i.getExtras();
        final String gelen = b.get("email").toString();
        final String gelentip = b.get("tip").toString();
        final String gelenisim = b.get("isim").toString();
        final Database d = new Database(this);

        final Button btn = (Button) findViewById(R.id.button6);


        final Spinner sp1 = (Spinner) findViewById(R.id.spinner2);
        final Spinner sp2 = (Spinner) findViewById(R.id.spinner3);

        String bolum = "";

        Cursor c = d.ogrAl(gelen);
        ArrayList<String> ar = new ArrayList<>();

        c.moveToFirst();
        bolum = c.getString(c.getColumnIndex("bolum"));


        ArrayAdapter<String> adapter;
        final List<String> ders;
        final String tip;

        String sorgu = "";

        if(gelentip.equals("LYS")){
            sorgu = "SELECT * FROM ders WHERE alan='"+bolum+"' AND tip='"+gelentip+"'";
        } else if(gelentip.equals("YGS")){
            sorgu = "SELECT * FROM ders WHERE alan is null AND tip='"+gelentip+"'";
        }
        ders = d.dersAl(sorgu);
        ders.add(0,"Ders Seçiniz");
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,ders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (ders.get(position).equals("Ders Seçiniz")){
                    Toast.makeText(Ders.this, "Lütfen Önce Ders Seçiniz.", Toast.LENGTH_SHORT).show();

                }else {
                    List<String> konu;
                    konu = d.dersAl("SELECT * FROM konu WHERE ders='"+ders.get(position)+"' AND sinav='"+gelentip+"'");
                    konu.add(0,"Konu Seçiniz");
                    ArrayAdapter<String> adapter1;

                    adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, konu);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp2.setAdapter(adapter1);

                    konu2 = konu;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (konu2.get(position).equals("Konu Seçiniz")){
                    Toast.makeText(Ders.this, "Lütfen Konu Seçiniz.", Toast.LENGTH_SHORT).show();

                }else{

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(Ders.this,KonuKayit.class);
                            in.putExtra("email",gelen);
                            in.putExtra("konu",konu2.get(position));
                            String derss = sp1.getSelectedItem().toString();
                            in.putExtra("ders",derss);
                            in.putExtra("tip",gelentip);
                            in.putExtra("isim",gelenisim);
                            Ders.this.startActivity(in);
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
