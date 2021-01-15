package com.example.demo.bean.bo;

import com.example.demo.util.Sheet;
import org.apache.poi.ss.usermodel.Font;

public class Elem {

    private String tagName;

    private ElemStyle s;

    private Attr attr;

    private String v;

    private Double avg;

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    private String hide;

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

    /**
     * 计算生成在表格里的换行字符的值。
     *
     * @param table
     * @return
     */
    private String getWrapValue(Table table, Font ft) {
        String v = "";

        if ("real".equals(this.getHide())) {
            v = this.getV();

            // 字符不为空，才有进行运算的必要；
            if (v != null) {
                Integer len = Sheet.getTextLength(v);
                ElemStyle s = this.getS();
                String size = table.getS().getFont().getSize();
                Float w = s.getW();

//                ft.

            }
        }

        return v;
    }
}
