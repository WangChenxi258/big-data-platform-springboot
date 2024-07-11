package com.buct.bigDataPlatform.Controller;


import com.buct.bigDataPlatform.Pojo.Result;
import com.buct.bigDataPlatform.Service.FileService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    private static final String UPLOAD_DIR = "D:\\coder\\java\\BigDataPlatform_spring_boot\\data\\";
    @PostMapping
    public Result getfile(@RequestParam("file") MultipartFile file) throws IOException {
//        categoryService.add(category);
        String fileName=file.getOriginalFilename();
        File dest = new File(UPLOAD_DIR + fileName);
        file.transferTo(dest);
        System.out.println(fileName);
        fileService.add(fileName,UPLOAD_DIR + fileName);
        return Result.OK("没问题");
    }

    @GetMapping("/detail")
    public Result detil(@RequestParam String id) throws IOException {
        String dir=fileService.getById(id);
        List<Map<String, String>> data = new ArrayList<>();

        FileInputStream file = new FileInputStream(dir);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue; // Skip if row is null

            Map<String, String> rowData = new LinkedHashMap<>();
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                Cell headerCell = headerRow.getCell(j);
                String header = headerCell != null ? headerCell.getStringCellValue() : "Unknown";
                String cellValue = cell != null ? cell.toString() : "";
                rowData.put(header, cellValue);
            }
            data.add(rowData);
        }
        workbook.close();
        return Result.OK(data);
    }
}
