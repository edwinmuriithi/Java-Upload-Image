package com.example.imageupload.repository;

import com.example.imageupload.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository <ImageData, Long>{
     Optional<ImageData> findByName(String fileName);
}
