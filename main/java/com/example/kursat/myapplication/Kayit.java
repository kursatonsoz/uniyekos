package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Kayit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayit);
        Button b = (Button) findViewById(R.id.button3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textView3);
                Ogrenci o = new Ogrenci();
                EditText adi = (EditText) findViewById(R.id.editText3);
                EditText eposta = (EditText) findViewById(R.id.editText4);
                EditText sifre = (EditText) findViewById(R.id.editText5);
                EditText sifret = (EditText) findViewById(R.id.editText6);
                Spinner sp = (Spinner) findViewById(R.id.spinner);
                Database d = new Database(Kayit.this);

                Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                Matcher m = p.matcher(eposta.getText());
                boolean b = m.matches();
                boolean k = true;
                String epos = eposta.getText().toString();
                Cursor data = d.ogrAl(epos);
                if (data!=null && data.moveToFirst()){
                    String ve = data.getString(data.getColumnIndex("email"));
                    if (ve.equals(epos)){
                        Toast.makeText(Kayit.this, "Bu e-posta adresi sistemde kayıtlıdır. Lütfen farklı bir e-posta adresi giriniz.", Toast.LENGTH_LONG).show();
                    }
                }

                if (!b || eposta.getText().equals("")){
                    k=false;
                }
                if (!sifre.getText().toString().equals(sifret.getText().toString())){
                    k=false;
                }


                if (k){
                    o.set_ogrAdi(adi.getText().toString());
                    o.set_email(eposta.getText().toString());
                    o.set_sifre(sifre.getText().toString());
                    o.set_department(sp.getSelectedItem().toString());
                    String did = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                    o.set_deviceiID(did);
                    d.ogrEkle(o);
                    Toast.makeText(Kayit.this,"Kayıt Başarılı.",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Kayit.this,MainActivity.class);


                    Kayit.this.startActivity(i);
                }else {
                    Toast.makeText(Kayit.this,"Lütfen E-mail adresinizi veya şifrenizi kontrol ediniz.",Toast.LENGTH_SHORT).show();
                }


            }

        });
    }
}