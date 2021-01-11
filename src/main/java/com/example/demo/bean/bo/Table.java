package com.example.demo.bean.bo;

import com.example.demo.util.Sheet;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<List<Elem>> thead;

    private List<List<Elem>> tbody;

    private ElemStyle s;

    public List<List<Elem>> getThead() {
        return thead;
    }

    public void setThead(List<List<Elem>> thead) {
        this.thead = thead;
    }

    public List<List<Elem>> getTbody() {
        return tbody;
    }

    public void setTbody(List<List<Elem>> tbody) {
        this.tbody = tbody;
    }

    public ElemStyle getS() {
        return s;
    }

    public void setS(ElemStyle s) {
        this.s = s;
    }



}
