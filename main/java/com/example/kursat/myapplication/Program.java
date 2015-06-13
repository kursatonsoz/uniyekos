package com.example.kursat.myapplication;


import com.example.kursat.myapplication.TabsPagerAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Program extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar"};
    public String derss = "Ders Seçiniz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program);


        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        Intent in = getIntent();
        final Bundle bu = in.getExtras();
        mAdapter.setEmail(bu.get("email").toString());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(Integer.parseInt(bu.get("day").toString()));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getIntent();
        final Bundle b = i.getExtras();
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            onBackPressed();
            return true;
        }
        if (id == R.id.action_add_line) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            LayoutInflater inflater = this.getLayoutInflater();

            final View fileView = getLayoutInflater().inflate(R.layout.program_add, null);


            alert.setView(fileView);


            final List<String> ders;
            final Spinner sp1 = (Spinner) fileView.findViewById(R.id.add_spinner);
            final Database d = new Database(this);
            ders = d.dersAl("select * from ders where alan is null");
            ders.add(0, "Ders Seçiniz");
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ders);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp1.setAdapter(adapter);

            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    if (!sp1.getSelectedItem().equals("Ders Seçiniz")) {
                        derss = sp1.getSelectedItem().toString();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            final TextView tvDisplayTime1 = (TextView) fileView.findViewById(R.id.tvTime1);
            final TextView tvDisplayTime2 = (TextView) fileView.findViewById(R.id.tvTime2);
            final TimePicker timePicker1 = (TimePicker) fileView.findViewById(R.id.timePicker1);
            final TimePicker timePicker2 = (TimePicker) fileView.findViewById(R.id.timePicker2);
            final EditText metin = (EditText) fileView.findViewById(R.id.program_notu);

            tvDisplayTime1.setText("Başlangıç Saati");
            tvDisplayTime2.setText("Bitiş Saati");


            alert.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    int hour1,hour2,minute1,minute2;
                    hour1 = timePicker1.getCurrentHour();
                    minute1 = timePicker1.getCurrentMinute();

                    hour2 = timePicker2.getCurrentHour();
                    minute2 = timePicker2.getCurrentMinute();

                    Database db = new Database(Program.this);

                    Prog p = new Prog();

                    p.setEmail(b.get("email").toString());
                    p.setDers(derss);
                    p.setBasla(String.valueOf(hour1) + ":" + String.valueOf(minute1));
                    p.setBitir(String.valueOf(hour2) + ":" + String.valueOf(minute2));
                    p.setGun(String.valueOf(viewPager.getCurrentItem()));
                    p.setNotlar(metin.getText().toString());
                    d.programEkle(p);
                    Toast.makeText(Program.this,"Program Başarıyla Eklendi.",Toast.LENGTH_LONG).show();
                    Intent intent = getIntent();
                    intent.putExtra("day",String.valueOf(viewPager.getCurrentItem()));
                    finish();
                    startActivity(intent);
                }
            });

            alert.setNegativeButton("İptal",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });

            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}