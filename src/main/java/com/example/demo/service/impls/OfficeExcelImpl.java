package com.example.demo.service.impls;

import com.example.demo.bean.ao.RequestInfo;
import com.example.demo.bean.bo.ExcelParam;
import com.example.demo.bean.bo.Seal;
import com.example.demo.bean.bo.Table;
import com.example.demo.service.OfficeExcel;
import com.example.demo.util.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeExcelImpl implements OfficeExcel {

    /**
     * 开始写excel.
     */
    @Override
    public String writeOfficeExcel(RequestInfo<ExcelParam> info) throws Exception {
        ExcelParam excelParam = info.getParameter();
        Seal seal = excelParam.getSeal();
        List<Table> tables = excelParam.getTables();
        String fileName = excelParam.getFileName();
        String author = "test";
        String relativePath = "/home/branches/exdemo/excel_download/" + author + "/" + System.currentTimeMillis() + "/";

        File fileDir = new File(relativePath);

        if (!fileDir.mkdirs()) {
            throw new Exception("创建路径失败");
        }

        String fileFullName = fileName + ".xlsx";
        File file = new File(relativePath, fileFullName);

        if (!file.createNewFile()) {
            throw new Exception("创建文件失败");
        }

        OutputStream out = new FileOutputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheetName1");
        Sheet.writeSheet(sheet, tables);
        workbook.write(out);
        out.flush();
        out.close();

        return "test...";
    }

}
