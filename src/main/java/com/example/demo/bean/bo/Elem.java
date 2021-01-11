package com.example.demo.bean.bo;

public class Elem {

    private String tagName;

    private ElemStyle s;

    private Attr attr;

    private String v;

    private Double avg;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ElemStyle getS() {
        return s;
    }

    public void setS(ElemStyle s) {
        this.s = s;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
