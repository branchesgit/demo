package com.example.demo.bean.bo;

import com.example.demo.util.Sheet;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<List<Elem>> thead;

    private List<List<Elem>> tbody;

    private ElemStyle s;

    private TableDesc t;

    public TableDesc getT() {
        return t;
    }

    public void setT(TableDesc t) {
        this.t = t;
    }

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


    public Integer getColumnNumber() {
        List<List<Elem>> thead = this.getThead();
        List<List<Elem>> tbody = this.getTbody();

        int col = 0;
        int index = 0;

        if (thead != null) {
            List<Elem> tr = thead.get(index);

            col = tr.size();
        }

        if (col == 0) {
            List<Elem> tr = tbody.get(index);
            col = tr.size();
        }

        return col;
    }

    public Integer getRowNumber() {
        List<List<Elem>> thead = this.getThead();
        List<List<Elem>> tbody = this.getTbody();

        Integer row = 0;

        if (thead != null) {
            row += thead.size();
        }

        if (tbody != null) {
            row += tbody.size();
        }

        return row;
    }

    /**
     * 找到行高，列宽
     *
     * @param table
     */
    public List<List<Float>> getTableStyle(Table table) {
        List<List<Elem>> thead = table.getThead();
        List<List<Elem>> tbody = table.getTbody();

        List<Float> rows = new ArrayList<>();
        List<Float> cols = new ArrayList<>();

        List<List<List<Elem>>> list = new ArrayList();
        list.add(thead);
        list.add(tbody);

        list.forEach(trs -> {
            if (trs != null) {
                for (int i = 0; i < trs.size(); i++) {
                    List<Elem> tr = trs.get(i);

                    for (int j = 0; j < tr.size(); j++) {
                        Elem elem = tr.get(j);

                        if (elem.getAttr() != null && elem.getAttr().getRowspan() == 1) {
                            if (elem.getS() != null) {
                                Float h = elem.getS().getH();

                                if (h != null) {
                                    rows.add(h);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });

        list.forEach(trs -> {
            if (trs != null) {
                for (int i = 0; i < trs.size(); i++) {
                    List<Elem> tr = trs.get(i);
                    int count = 0;

                    for (int j = 0; j < tr.size(); j++) {
                        Elem elem = tr.get(j);

                        if ("real".equals(elem.getHide())) {
                            count++;
                        }

                        if (count == tr.size()) {
                            tr.forEach(cell -> {
                                cols.add(cell.getS().getW());
                            });

                            break;
                        }
                    }

                    if (cols.size() > 0) {
                        break;
                    }
                }
            }
        });

        List<List<Float>> ary = new ArrayList<>();
        ary.add(rows);
        ary.add(cols);

        return ary;
    }
}
