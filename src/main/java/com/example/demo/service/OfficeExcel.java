package com.example.demo.service;

import com.example.demo.bean.ao.RequestInfo;
import com.example.demo.bean.bo.ExcelParam;
import com.example.demo.bean.bo.Seal;
import com.example.demo.bean.bo.Table;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OfficeExcel {

    String writeOfficeExcel(@RequestBody RequestInfo<ExcelParam> info) throws Exception;

}
