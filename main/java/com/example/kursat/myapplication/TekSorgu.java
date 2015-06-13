package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kursat on 12/22/14.
 */
public class TekSorgu extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teksorgu);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String gelen = b.get("email").toString();
        final String gelentip = b.get("tip").toString();
        final String gelenders = b.get("ders").toString();
        String gel = b.get("konu").toString();
        final String gelenkonu = gel;
        final String gelensecim = b.get("secim").toString();


        TextView tv = (TextView) findViewById(R.id.sstextView9);


        EditText e1 = (EditText) findViewById(R.id.sseditText7);
        EditText e2 = (EditText) findViewById(R.id.sseditText8);
        EditText e3 = (EditText) findViewById(R.id.sseditText9);
        EditText e4 = (EditText) findViewById(R.id.sseditText10);
        EditText e5 = (EditText) findViewById(R.id.sseditText11);
        EditText e6 = (EditText) findViewById(R.id.sseditText12);

        TextView t1 = (TextView) findViewById(R.id.textView19);
        TextView t2 = (TextView) findViewById(R.id.textView21);


        Database d = new Database(this);
        Kaydet k = new Kaydet();
        k.setEmail(gelen);
        k.setTip(gelentip);
        k.setDers(gelenders);
        k.setKonu(gelenkonu);
        k.setTarih(gelensecim);
        Cursor c = d.kayitAl(k);
        //tv.setText("tarih->"+gelensecim);


        if (c!=null && c.moveToFirst()){

            c.moveToFirst();
            int cal=0,sor=0,coz=0,dog=0,yan=0,bo=0;
            String tar="";
            tar = c.getString(c.getColumnIndex("tarih"));
            List<String> konular = new ArrayList<String>();

            Log.e("asd",String.valueOf(c.getCount()));
            do {
                cal+=Integer.parseInt(c.getString(c.getColumnIndex("calsur")));
                sor+=Integer.parseInt(c.getString(c.getColumnIndex("soru")));
                coz+=Integer.parseInt(c.getString(c.getColumnIndex("cozme")));
                dog+=Integer.parseInt(c.getString(c.getColumnIndex("dogru")));
                yan+=Integer.parseInt(c.getString(c.getColumnIndex("yanlis")));
                bo+=Integer.parseInt(c.getString(c.getColumnIndex("bos")));
                konular.add(c.getString(c.getColumnIndex("konu")));

            }while (c.moveToNext());

            StringBuilder builder = new StringBuilder();
            builder.append( konular.remove(0));

            for( String s : konular) {
                builder.append( ", ");
                builder.append( s);
            }

            tv.setText(gelensecim);
            t1.setText(gelenders);
            t2.setText(builder.toString());
            e1.setText(String.valueOf(cal));
            e2.setText(String.valueOf(sor));
            e3.setText(String.valueOf(coz));
            e4.setText(String.valueOf(dog));
            e5.setText(String.valueOf(yan));
            e6.setText(String.valueOf(bo));
        }


    }
}
