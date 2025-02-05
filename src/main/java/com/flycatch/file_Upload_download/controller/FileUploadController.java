package com.flycatch.file_Upload_download.controller;

import ch.qos.logback.core.util.StringUtil;
import com.flycatch.file_Upload_download.fileUpload.FileUploadUtil;
import com.flycatch.file_Upload_download.response.FileUploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
public class FileUploadController {

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file")MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        long size = multipartFile.getSize();
        String fileCode = FileUploadUtil.saveFile(fileName,multipartFile);
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUrl("/downloadFile/"+fileCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
