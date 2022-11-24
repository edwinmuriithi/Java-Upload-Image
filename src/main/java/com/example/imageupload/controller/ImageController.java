package com.example.imageupload.controller;

import com.example.imageupload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;


    @PostMapping("/image")
    public ResponseEntity<?> uploading(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = imageService.downloadImage(fileName);
        return  ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
}
}
