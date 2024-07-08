package com.buct.bigDataPlatform.Controller;

import com.buct.bigDataPlatform.Pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-13 16:18
 */
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件内容存到本地磁盘
        String originalFilename = file.getOriginalFilename();
        //保证文件名字唯一
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("C:\\Users\\18050\\Desktop\\files\\" + filename));
        return Result.OK("C:\\Users\\18050\\Desktop\\files\\" + filename);
    }
}