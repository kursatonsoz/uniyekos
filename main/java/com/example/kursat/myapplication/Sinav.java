package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kursat on 12/21/14.
 */
public class Sinav extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sinav);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b!=null){
            final String idsi = b.get("email").toString();
            TextView tv8 = (TextView) findViewById(R.id.textView8);
            final String isim = b.get("isim").toString();
            tv8.setText("Hazır mısın ? "+isim);
            final String _strYgs = "YGS";
            final String _strLys = "LYS";
            final String _strNothing = "NA";

            Button ygs = (Button) findViewById(R.id.button4);
            ygs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Sinav.this,Ders.class);
                    in.putExtra("email",idsi);
                    in.putExtra("tip",_strYgs);
                    in.putExtra("isim", isim);
                    Sinav.this.startActivity(in);
                    finish();
                }
            });
            Button lys =(Button) findViewById(R.id.button7);
            lys.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Sinav.this,Ders.class);
                    in.putExtra("email",idsi);
                    in.putExtra("tip",_strLys);
                    in.putExtra("isim",isim);
                    Sinav.this.startActivity(in);
                    finish();
                }
            });

            Button durum = (Button) findViewById(R.id.button5);
            durum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Sinav.this,Sorgu.class);
                    in.putExtra("email",idsi);
                    in.putExtra("tip",_strNothing);
                    Sinav.this.startActivity(in);
                    finish();
                }
            });

            Button prog = (Button) findViewById(R.id.button9);
            prog.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(Sinav.this,Program.class);
                    in.putExtra("email",idsi);
                    in.putExtra("tip", _strNothing);
                    in.putExtra("day","0");
                    in.putExtra("isim",isim);
                    Sinav.this.startActivity(in);
                    //finish();
                }
            });
        }
    }
}