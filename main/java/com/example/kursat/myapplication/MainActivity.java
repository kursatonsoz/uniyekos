package com.example.kursat.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.Settings.Secure;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database d = new Database(MainActivity.this);

        setContentView(R.layout.activity_main);
        Button grafBtn=(Button) findViewById(R.id.button8);
        Button b = (Button) findViewById(R.id.button2);
        Button g = (Button) findViewById(R.id.button);
        String cihaz_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        Cursor data1 = d.cidAL(cihaz_id);
        if(data1 != null && data1.moveToFirst()) {

            if (cihaz_id.equals(data1.getString(data1.getColumnIndex("device_id")))) {
                //grafBtn.setText(cihaz_id);
                Intent a = new Intent(MainActivity.this, Sinav.class);
                a.putExtra("email",data1.getString(data1.getColumnIndex("email")));
                a.putExtra("isim",data1.getString(data1.getColumnIndex("adsoyad")));
                MainActivity.this.startActivity(a);
                finish();
            }
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Kayit.class);
                MainActivity.this.startActivity(i);
                finish();
            }
        });

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Sinav.class);
                Database d = new Database(MainActivity.this);
                EditText e = (EditText) findViewById(R.id.editText);
                String em = e.getText().toString();
                EditText s = (EditText) findViewById(R.id.editText2);
                String si = s.getText().toString();
                Cursor data = d.ogrAl(em);
                if (data != null && data.moveToFirst()) {
                    String ge = data.getString(data.getColumnIndex("email"));
                    String sifre = data.getString(data.getColumnIndex("sifre"));
                    if (ge.equals(em) && sifre.equals(si)) {

                        i.putExtra("email", ge);
                        i.putExtra("isim",data.getString(data.getColumnIndex("adsoyad")));
                        MainActivity.this.startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Lütfen Giriş Bilgilerinizi Kontrol ediniz.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Lütfen Giriş Bilgilerinizi Kontrol ediniz.", Toast.LENGTH_LONG).show();
                }


            }
        });

        grafBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,Grafik.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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