package com.example.kursat.myapplication;

/**
 * Created by kursat on 31.05.2015.
 */

import com.example.kursat.myapplication.R;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragPazartesi extends Fragment {

    final List<Prog> prog = new ArrayList<Prog>();

    public String email_;

    public FragPazartesi(String email) {
        this.email_ = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.frag_pazartesi, container, false);
        Database d = new Database(super.getActivity());
        Cursor c = d.programCek("0",this.email_);
        prog.clear();
        Prog p = new Prog();
        p.setEmail(this.email_);
        p.setGun("0");
        p.setDers("Bu Güne Ait Kayıtlı Program Bulunamadı");
        p.setBasla("");
        p.setBitir("");
        p.setNotlar("");
        prog.add(p);
        if (c!=null && c.moveToFirst()) {
            c.moveToFirst();
            prog.clear();
            do {
                p = new Prog();
                p.setEmail(this.email_);
                p.setGun("0");
                p.setDers(c.getString(c.getColumnIndex("ders")));
                p.setBasla(c.getString(c.getColumnIndex("basla")));
                p.setBitir(c.getString(c.getColumnIndex("bitir")));
                p.setNotlar(c.getString(c.getColumnIndex("notlar")));
                prog.add(p);
                Log.e("PAZARTESI-->",c.getString(c.getColumnIndex("notlar")));
            }while (c.moveToNext());
        }

        final ListView listem = (ListView) rootView.findViewById(R.id.pzt_liste);
        customAdapter cs = new customAdapter(super.getActivity(),prog);
        listem.setAdapter(cs);
        return rootView;
    }
}