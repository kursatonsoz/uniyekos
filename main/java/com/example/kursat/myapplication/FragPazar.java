package com.example.kursat.myapplication;

/**
 * Created by kursat on 31.05.2015.
 */

import com.example.kursat.myapplication.R;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragPazar extends Fragment {

    final List<Prog> prog = new ArrayList<Prog>();

    public String email_;

    public FragPazar(String email) {
        this.email_ = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_pazar, container, false);
        Database d = new Database(super.getActivity());
        Cursor c = d.programCek("6",this.email_);
        prog.clear();
        Prog p = new Prog();
        p.setEmail(this.email_);
        p.setGun("6");
        p.setDers("Bu Güne Ait Kayıtlı Program Bulunamadı");
        p.setBasla("");
        p.setBitir("");
        p.setNotlar("");
        prog.add(p);
        if (c!=null && c.moveToFirst()) {
            prog.clear();
            do {
                p = new Prog();
                p.setEmail(this.email_);
                p.setGun("6");
                p.setDers(c.getString(c.getColumnIndex("ders")));
                p.setBasla(c.getString(c.getColumnIndex("basla")));
                p.setBitir(c.getString(c.getColumnIndex("bitir")));
                p.setNotlar(c.getString(c.getColumnIndex("notlar")));
                prog.add(p);
            }while (c.moveToNext());
            c.moveToLast();
        }

        final ListView listem = (ListView) rootView.findViewById(R.id.pazar_liste);
        customAdapter cs = new customAdapter(super.getActivity(),prog);
        listem.setAdapter(cs);
        return rootView;
    }
}