package com.example.demo.util;

import com.example.demo.bean.bo.Elem;
import com.example.demo.bean.bo.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;


public class Sheet {


    /**
     * 已经处理好的单元格子，　将数据写进去。
     *
     * @param tables
     */
    public static boolean writeSheet(XSSFSheet sheet, List<Table> tables) {

        if (tables == null) {
            return false;
        }

        int size = tables.size();

        for (Integer i = 0; i < size; i++) {
            Table table = tables.get(i);

            List<List<Elem>> thead = table.getThead();
            List<List<Elem>> tbody = table.getTbody();
            int col = Sheet.getMaxCol(thead, tbody);
            Sheet.writeTheadOrTbody(thead, sheet, col, 0);
            Sheet.writeTheadOrTbody(tbody, sheet, col, thead.size());
        }

        return true;
    }

    /**
     * 写thead/tbody的数据；
     */
    static void writeTheadOrTbody(List<List<Elem>> trs, XSSFSheet sheet, int col, int startIdx) {
        if (trs == null) {
            return;
        }

        /**
         * 暂不处理跨行跨列的
         */

        for (int r = startIdx; r < trs.size(); r++) {
            List<Elem> tr = trs.get(r);
            Row row = sheet.createRow(r);
            int colCount = 0;

            for (int j = 0; j < col; j++) {
                Cell cell = row.createCell(j);
                Elem elem = Sheet.getReallyElem(tr, j);

                /**
                 * 找到对应的数据，那么就把这个
                 */
                if (elem != null) {
                    System.out.println(elem.getV());
                    cell.setCellValue(elem.getV());
                }
            }
        }

    }

    static Elem getReallyElem(List<Elem> tr, int col) {
        if (tr == null) {
            return null;
        }

        int start = 0;
        int end = 0;
        Elem elem = null;

        for (int i = 0; i < tr.size(); i++) {
            Elem el = tr.get(i);
            end += el.getAttr().getColspan();

            if (start == col || start < col  && col < end) {
                elem = el;
                start += el.getAttr().getColspan();
                break;
            }

            start += el.getAttr().getColspan();
        }

        return elem;
    }

    static int getMaxCol(List<List<Elem>> thead, List<List<Elem>> tbody) {
        int col = 0;
        int index = 0;

        if (thead != null) {
            List<Elem> tr = thead.get(index);

            for (int i = 0; i < tr.size(); i++) {
                Elem elem = tr.get(i);
                col += elem.getAttr().getColspan();
            }

        }

        if (col == 0) {
            List<Elem> tr = tbody.get(index);

            for (int i = 0; i < tr.size(); i++) {
                Elem elem = tr.get(i);
                col += elem.getAttr().getColspan();
            }
        }

        return col;
    }
}
