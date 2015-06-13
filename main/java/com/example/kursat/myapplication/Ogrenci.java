package com.example.kursat.myapplication;

/**
 * Created by kursat on 12/20/14.
 */
public class Ogrenci {
    Integer _ogrID;
    String _ogrAdi;
    String _sifre;
    String _department;
    String _email;
    String _deviceiID;

    public Ogrenci(){
        this.set_email("kursat");
    }
    public String get_deviceiID() {
        return _deviceiID;
    }

    public void set_deviceiID(String _deviceiID) {
        this._deviceiID=_deviceiID;
    }
    public Integer get_ogrID() {
        return _ogrID;
    }

    public void set_ogrID(Integer _ogrID) {
        this._ogrID = _ogrID;
    }

    public String get_ogrAdi() {
        return _ogrAdi;
    }

    public void set_ogrAdi(String _ogrAdi) {
        this._ogrAdi = _ogrAdi;
    }

    public String get_sifre() {
        return _sifre;
    }

    public void set_sifre(String _sifre) {
        this._sifre = _sifre;
    }

    public String get_department() {
        return _department;
    }

    public void set_department(String _department) {
        this._department = _department;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
