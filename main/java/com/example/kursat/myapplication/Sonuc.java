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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by kursat on 12/21/14.
 */
public class Sonuc extends Activity {
    private String derss, konus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonuc);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String gelen = b.get("email").toString();
        final String gelentip = b.get("tip").toString();
        final String gelenders = b.get("ders").toString();
        final String gelenkonu = b.get("konu").toString();

        TextView tarih = (TextView) findViewById(R.id.stextView8);
        EditText calis = (EditText) findViewById(R.id.seditText7);
        EditText soru = (EditText) findViewById(R.id.seditText8);
        EditText cozme = (EditText) findViewById(R.id.seditText9);
        EditText dogru = (EditText) findViewById(R.id.seditText10);
        EditText yanlis = (EditText) findViewById(R.id.seditText11);
        EditText bos = (EditText) findViewById(R.id.seditText12);
        final Spinner sec = (Spinner) findViewById(R.id.spinner6);

        if (gelenders.equals("Ders Seçiniz")) {
            //konuya göre ara
            Database d = new Database(this);
            Cursor c = d.konular(gelenkonu, gelen);
            konus = gelenkonu;
            if (c != null && c.moveToFirst()) {
                c.moveToFirst();
                int cal = 0, sor = 0, coz = 0, dog = 0, yan = 0, bo = 0;
                String tar = "";
                tar = c.getString(c.getColumnIndex("tarih"));
                List<String> liste = new ArrayList<>();
                liste.add(tar);

                do {
                    cal += Integer.parseInt(c.getString(c.getColumnIndex("calsur")));
                    sor += Integer.parseInt(c.getString(c.getColumnIndex("soru")));
                    coz += Integer.parseInt(c.getString(c.getColumnIndex("cozme")));
                    dog += Integer.parseInt(c.getString(c.getColumnIndex("dogru")));
                    yan += Integer.parseInt(c.getString(c.getColumnIndex("yanlis")));
                    bo += Integer.parseInt(c.getString(c.getColumnIndex("bos")));
                    liste.add(c.getString(c.getColumnIndex("tarih")));
                    derss = c.getString(c.getColumnIndex("ders"));

                } while (c.moveToNext());
                c.moveToLast();
                String son = c.getString(c.getColumnIndex("tarih"));
                liste.add(son);
                HashSet hs = new HashSet();
                hs.addAll(liste);
                liste.clear();
                liste.addAll(hs);
                tar = smallDate(liste) + " ile " + bigDate(liste);
                liste.add(0, "Tarih");
                ArrayAdapter<String> adapter1;
                adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, liste);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sec.setAdapter(adapter1);

                tarih.setText(tar);
                calis.setText(String.valueOf(cal));
                soru.setText(String.valueOf(sor));
                cozme.setText(String.valueOf(coz));
                dogru.setText(String.valueOf(dog));
                yanlis.setText(String.valueOf(yan));
                bos.setText(String.valueOf(bo));
            } else {
                Toast.makeText(this, "Bu konuyla ilgili kayıt yoktur.", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Sonuc.this, MainActivity.class);
                Sonuc.this.startActivity(in);
            }

        }
        if (gelenkonu.equals("Konu Seçiniz")) {
            //derse göre ara
            Database d = new Database(this);
            Cursor c = d.dersler(gelenders, gelen);
            derss = gelenders;
            if (c != null && c.moveToFirst()) {
                c.moveToFirst();
                int cal = 0, sor = 0, coz = 0, dog = 0, yan = 0, bo = 0;
                String tar = "";
                tar = c.getString(c.getColumnIndex("tarih"));
                List<String> liste = new ArrayList<>();
                liste.add(tar);

                do {
                    cal += Integer.parseInt(c.getString(c.getColumnIndex("calsur")));
                    sor += Integer.parseInt(c.getString(c.getColumnIndex("soru")));
                    coz += Integer.parseInt(c.getString(c.getColumnIndex("cozme")));
                    dog += Integer.parseInt(c.getString(c.getColumnIndex("dogru")));
                    yan += Integer.parseInt(c.getString(c.getColumnIndex("yanlis")));
                    bo += Integer.parseInt(c.getString(c.getColumnIndex("bos")));
                    liste.add(c.getString(c.getColumnIndex("tarih")));
                    konus = "Hepsi";

                } while (c.moveToNext());
                c.moveToLast();
                String son = c.getString(c.getColumnIndex("tarih"));


                liste.add(son);
                HashSet hs = new HashSet();
                hs.addAll(liste);
                liste.clear();
                liste.addAll(hs);
                tar = smallDate(liste) + " ile " + bigDate(liste);
                liste.add(0, "Tarih");
                ArrayAdapter<String> adapter1;
                adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, liste);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sec.setAdapter(adapter1);
                tarih.setText(tar);
                calis.setText(String.valueOf(cal));

                soru.setText(String.valueOf(sor));
                cozme.setText(String.valueOf(coz));
                dogru.setText(String.valueOf(dog));
                yanlis.setText(String.valueOf(yan));
                bos.setText(String.valueOf(bo));
            } else {
                Toast.makeText(this, "Bu dersle ilgili kayıt yoktur.", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Sonuc.this, MainActivity.class);
                Sonuc.this.startActivity(in);
            }

        }

        Button cilgin_devlet_halkini_costuruyor = (Button) findViewById(R.id.button10);

        cilgin_devlet_halkini_costuruyor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sonuc.this,Grafik.class);
                i.putExtra("gun","ananı sikem");
                Sonuc.this.startActivity(i);
            }
        });

        sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!sec.getSelectedItem().toString().equals("Tarih")) {
                    Intent i = new Intent(Sonuc.this, TekSorgu.class);
                    i.putExtra("secim", sec.getSelectedItem().toString());
                    i.putExtra("email", gelen);
                    i.putExtra("ders", derss);
                    i.putExtra("konu", konus);
                    i.putExtra("tip", gelentip);
                    Sonuc.this.startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public String bigDate(List<String> ar) {
        Date date1 = new Date();
        String enbuyuk = ar.get(0);
        DateFormat format = new SimpleDateFormat("dd/MMMM/yyyy", new Locale("tr"));
        for (String iter : ar) {
            try {
                date1 = format.parse(iter);
                if (date1.after(format.parse(enbuyuk))) {
                    enbuyuk = format.format(date1);
                }
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }

        return enbuyuk;
    }

    public String smallDate(List<String> ar) {
        Date date1 = new Date();
        String enkucuk = ar.get(0);
        DateFormat format = new SimpleDateFormat("dd/MMMM/yyyy", new Locale("tr"));
        for (String iter : ar) {
            try {
                date1 = format.parse(iter);
                if (date1.before(format.parse(enkucuk))) {
                    enkucuk = format.format(date1);
                }
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }

        return enkucuk;
    }

}
