package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kursat on 12/21/14.
 */
public class KonuKayit extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konukayit);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String gelenemail = b.get("email").toString();
        final String gelenkonu = b.get("konu").toString();
        final String gelenders = b.get("ders").toString();
        final String gelentip = b.get("tip").toString();
        final String gelenisim = b.get("isim").toString();

        TextView tvtarih = (TextView) findViewById(R.id.textView9);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy", new Locale("tr"));

        final String tarih = sdf.format(new Date());
        tvtarih.setText(tarih);

        Button but = (Button) findViewById(R.id.button4);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText calismaSure = (EditText) findViewById(R.id.editText7);
                EditText soruSayisi = (EditText) findViewById(R.id.editText8);
                EditText cozumSure = (EditText) findViewById(R.id.editText9);
                EditText dogruSayisi = (EditText) findViewById(R.id.editText10);
                EditText yanlisSayisi = (EditText) findViewById(R.id.editText11);
                EditText bosSayisi = (EditText) findViewById(R.id.editText12);


                Database d = new Database(KonuKayit.this);

                Kaydet kaydet = new Kaydet();

                kaydet.setTarih(tarih);
                kaydet.setCalsur(calismaSure.getText().toString());
                kaydet.setSoru(soruSayisi.getText().toString());
                kaydet.setCozme(cozumSure.getText().toString());
                kaydet.setDogru(dogruSayisi.getText().toString());
                kaydet.setYanlis(yanlisSayisi.getText().toString());
                kaydet.setBos(bosSayisi.getText().toString());
                kaydet.setEmail(gelenemail);
                kaydet.setKonu(gelenkonu);
                kaydet.setDers(gelenders);
                kaydet.setTip(gelentip);

                if (kaydet.getCalsur().equals("") || kaydet.getCozme().equals("") || kaydet.getSoru().equals("")
                        || kaydet.getDogru().equals("") || kaydet.getYanlis().equals("") || kaydet.getBos().equals("")) {
                    Toast.makeText(KonuKayit.this, "Tüm alanları doldurmak zorunludur!", Toast.LENGTH_SHORT).show();

                } else {
                    int soruSayisi_int = Integer.parseInt(soruSayisi.getText().toString());
                    int dogruSayisi_int = Integer.parseInt(dogruSayisi.getText().toString());
                    int yanlisSayisi_int = Integer.parseInt(yanlisSayisi.getText().toString());
                    int bosSayisi_int = Integer.parseInt(bosSayisi.getText().toString());
                    int calismaSure_int = Integer.parseInt(calismaSure.getText().toString());
                    int cozumSure_int = Integer.parseInt(cozumSure.getText().toString());
                    int toplam = dogruSayisi_int + yanlisSayisi_int + bosSayisi_int;

                    d.kayitEkle(kaydet);

                    if (soruSayisi_int >= dogruSayisi_int && soruSayisi_int >= yanlisSayisi_int &&
                            soruSayisi_int >= bosSayisi_int && calismaSure_int >= cozumSure_int && toplam == soruSayisi_int) {
                        Toast.makeText(KonuKayit.this, "Kayıt Başarılı.", Toast.LENGTH_SHORT).show();
                        Intent inen = new Intent(KonuKayit.this, Sinav.class);
                        inen.putExtra("email", gelenemail);
                        inen.putExtra("isim",gelenisim);
                        //inen.putExtra("tip", gelentip);
                        KonuKayit.this.startActivity(inen);
                        finish();
                    }

                }
            }
        });
    }
}

