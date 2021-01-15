package com.example.demo.bean.bo;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class Font {

    private String align;

    private Integer weight;

    private String size;

    private String color;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public short handleAlign() {
        String align = this.getAlign();

        if (align == null) {
            align = "left";
        }

        align = align.toLowerCase();
        if ("left".equals(align)) {
            return CellStyle.ALIGN_LEFT;
        } else if ("right".equals(align)) {
            return CellStyle.ALIGN_RIGHT;
        } else if ("center".equals(align)) {
            return CellStyle.ALIGN_CENTER;
        }

        return CellStyle.ALIGN_LEFT;
    }
}
