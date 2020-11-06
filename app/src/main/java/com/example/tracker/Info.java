package com.example.tracker;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Info{
    private String itmname,itmquantity,itmtype;

    public Info() {
    }

    public Info(String itmname, String itmquantity, String itmtype) {
        this.itmname = itmname;
        this.itmquantity = itmquantity;
        this.itmtype = itmtype;
    }

    public String getitmname() {
        return itmname;
    }

    public void setitmname(String itmname) {
        this.itmname = itmname;
    }

    public String getitmquantity() {
        return itmquantity;
    }

    public void setitmquantity(String itmquantity) {
        this.itmquantity = itmquantity;
    }

    public String getitmtype() {
        return itmtype;
    }

    public void setitmtype(String itmtype) {
        this.itmtype = itmtype;
    }
}