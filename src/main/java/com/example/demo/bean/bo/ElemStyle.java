package com.example.demo.bean.bo;

public class ElemStyle {
    private Float w;

    private Float h;

    private String border;

    private String backgroundColor;

    private Font font;

    public Float getW() {
        return w;
    }

    public void setW(Float w) {
        this.w = w;
    }

    public Float getH() {
        return h;
    }

    public void setH(Float h) {
        this.h = h;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String handleRgb() {
        String color = this.getBackgroundColor();

        if (color == null) {
            return "0xffffff";
        } else {

            if (color.indexOf("rgba") > -1) {
                System.out.println(this);
                return "0xffffff";
            } else {
                color = color.replace("rgb(", "").replace(")", "");
                String[] ary = color.split(",");
                StringBuilder backgroundColor = new StringBuilder("0x");

                for (int i = 0; i < ary.length; i++) {
                    backgroundColor.append(Integer.toHexString(Integer.parseInt(ary[i].trim())));
                }

                return backgroundColor.toString();
            }
        }
    }

    @Override
    public String toString() {
        return "backgroundColor: " + this.backgroundColor + ", " +
                "height: " + this.h + ", width: " + this.w;
    }
}
