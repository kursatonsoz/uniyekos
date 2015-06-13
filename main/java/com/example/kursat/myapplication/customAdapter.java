package com.example.kursat.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kursat on 31.05.2015.
 */
public class customAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Prog> mProgram;

    public customAdapter(Activity activity, List<Prog> progs) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mProgram = progs;
    }

    @Override
    public int getCount() {
        return mProgram.size();
    }

    @Override
    public Prog getItem(int i) {
        return mProgram.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View satirView;
        satirView = mInflater.inflate(R.layout.prog_layout,null);
        TextView ders = (TextView) satirView.findViewById(R.id.prog_ders);
        TextView saat = (TextView) satirView.findViewById(R.id.prog_saat);
        TextView notlar = (TextView) satirView.findViewById(R.id.prog_notlar);
        Prog p = mProgram.get(i);
        ders.setText(p.getDers());
        saat.setText(p.getBasla()+":"+p.getBitir());
        notlar.setText(p.getNotlar());
        return satirView;
    }
}
