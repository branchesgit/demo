package com.example.demo.util;

import com.example.demo.bean.bo.*;
import com.example.demo.bean.bo.Font;
import com.example.demo.bean.bo.Table;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;


public class Sheet {

    private Sheet() {
    }

    private static Sheet instance;

    public static Sheet getInstance() {
        if (Sheet.instance == null) {
            Sheet.instance = new Sheet();
        }

        return Sheet.instance;
    }

    /**
     * 已经处理好的单元格子，　将数据写进去。
     *
     * @param tables
     */
    public boolean writeSheet(XSSFSheet sheet, List<Table> tables, XSSFWorkbook workbook) {

        if (tables == null) {
            return false;
        }

        int size = tables.size();
        int excelRowIndex = 0;

        for (Integer i = 0; i < size; i++) {
            Table table = tables.get(i);

            List<List<Float>> list = table.getTableStyle(table);
            List<List<Elem>> thead = table.getThead();
            List<List<Elem>> tbody = table.getTbody();
            int col = table.getColumnNumber();
            this.writeHeadOrBody(thead, sheet, col, 0, workbook, list, excelRowIndex, table);
            this.writeHeadOrBody(tbody, sheet, col, thead != null ? thead.size() : 0, workbook, list, excelRowIndex, table);

            excelRowIndex += table.getRowNumber();
        }

        return true;
    }


    /**
     * 写thead/tbody的数据；
     */
    private void writeHeadOrBody(List<List<Elem>> trs, XSSFSheet sheet, int col, int startIdx,
                                 XSSFWorkbook workbook, List<List<Float>> list, Integer excelRowIndex, Table table) {
        if (trs == null) {
            return;
        }

        int idx = 0;
        List<Float> rows = list.get(idx++);
        List<Float> cols = list.get(idx);


        for (int index = 0; index < cols.size(); index++) {
            sheet.setColumnWidth(index, Math.round(cols.get(index) * 256 / 8));
        }

        for (int r = 0; r < trs.size(); r++) {
            List<Elem> tr = trs.get(r);
            Row row = sheet.createRow(r + startIdx + excelRowIndex);
            Float rowHeight = rows.get(r + startIdx);
            row.setHeightInPoints(rowHeight * 72 / 96);

            for (int j = 0; j < col; j++) {
                Cell cell = row.createCell(j);
                Elem elem = tr.get(j);

                if (elem != null) {
                    if ("real".equals(elem.getHide())) {
                        System.out.println("x = " + r + ", y = " + j + ", v = " + elem.getV());
                        this.writeCellWithElem(cell, elem, workbook, table);
                    }
                }
            }
        }

        this.mergeTheadOrTbody(trs, sheet, startIdx, excelRowIndex);
    }


    /**
     * 将样式，高度设置好，
     *
     * @param cell
     * @param elem
     */
    void writeCellWithElem(Cell cell, Elem elem, XSSFWorkbook workbook, Table table) {
        cell.setCellValue(elem.getV());
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
//        style.setBorderTop(BorderStyle.THIN);
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//        style.setBorderLeft(BorderStyle.THIN);
        ElemStyle elStyle = elem.getS();

        if (elStyle != null) {
//            ((XSSFCellStyle) style).setFillBackgroundColor(new XSSFColor(java.awt.Color.decode(elStyle.handleRgb())));
//            style.setFillPattern(CellStyle.THIN_BACKWARD_DIAG);

            Font font = elStyle.getFont();

            if (font != null) {
                style.setAlignment(font.handleAlign());
                org.apache.poi.ss.usermodel.Font ft = workbook.createFont();
                short size = Short.parseShort(table.getS().getFont().getSize(), 10);
                size = (short) Math.max(4, size);
                ft.setFontHeightInPoints((short) Math.floor((double) size / 4 * 3));
                ft.setItalic(false);
                style.setFont(ft);
            }
        }

        cell.setCellStyle(style);
    }

    /**
     * 合并单元格逻辑；
     *
     * @param trs
     * @param sheet
     */
    void mergeTheadOrTbody(List<List<Elem>> trs, XSSFSheet sheet, int startIdx, int excelRowIndex) {
        if (trs == null) {
            return;
        }

        //　需要整体合并，先找出单元格子，进行合并，

        /**
         * size...
         */
        for (int i = 0; i < trs.size(); i++) {
            List<Elem> tr = trs.get(i);

            for (int j = 0; j < tr.size(); j++) {
                Elem elem = tr.get(j);
                Attr attr = elem.getAttr();

                if (attr != null && "real".equals(elem.getHide())) {
                    Integer rowspan = attr.getRowspan();
                    Integer colspan = attr.getColspan();

                    if (colspan != null && rowspan != null) {
                        if (colspan > 1 || rowspan > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(excelRowIndex + startIdx + i, excelRowIndex + startIdx + i + rowspan - 1,
                                    j, j + colspan - 1));

                        }
                    }
                }
            }
        }
    }

    /**
     * 中文算2 其他算1
     *
     * @param value
     * @return
     */
    public static int getTextLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }


}
