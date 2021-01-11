package com.example.demo.controller;

import com.example.demo.bean.ao.RequestInfo;
import com.example.demo.bean.bo.ExcelParam;
import com.example.demo.bean.vo.ResultContent;
import com.example.demo.service.OfficeExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeExcel officeExcel;

    @RequestMapping(value="/download/excel", method = RequestMethod.POST)
    public ResultContent<String> downloadExcel(@RequestBody RequestInfo<ExcelParam> info) {

        try {
            String filePath = officeExcel.writeOfficeExcel(info);

            return ResultContent.createSuccessResult(filePath);

        } catch (Exception e) {
            e.printStackTrace();
            String message = "异常错误";

            return ResultContent.createErrorResult(message);
        }
    }
}
