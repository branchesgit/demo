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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeExcel officeExcel;

    @RequestMapping(value = "/excel/download", method = RequestMethod.POST)
    public void downloadExcel(@RequestBody RequestInfo<ExcelParam> info, HttpServletResponse response) {

        ServletOutputStream out = null;
        FileInputStream ips = null;

        try {
            String filePath = officeExcel.writeOfficeExcel(info);

            if (filePath != null) {
                String fileName = info.getParameter().getFileName();
                fileName += ".xlsx";


                File file = new File(filePath);
                ips = new FileInputStream(file);
                response.setContentType("multipart/form-data");
                response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"");
                out = response.getOutputStream();
                //读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = ips.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
            String message = "异常错误";


        } finally {
            try {
                out.close();
                ips.close();
            } catch (IOException e) {
                System.out.println("关闭流出现异常");
                e.printStackTrace();
            }
        }


        return;
    }
}
