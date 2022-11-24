package com.example.imageupload.service;

import com.example.imageupload.models.ImageData;
import com.example.imageupload.repository.ImageRepository;
import com.example.imageupload.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile multiPartFile) throws IOException {
        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(multiPartFile.getOriginalFilename())
                .type(multiPartFile.getContentType())
                .imageData(ImageUtils.compressImage(multiPartFile.getBytes())).build());
            if (imageData!=null){
                return  "file uploaded successfully :"+ multiPartFile.getOriginalFilename();
            }
            return null;
    }

    public byte[] downloadImage (String fileName){
        Optional <ImageData> dbImageData = imageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
