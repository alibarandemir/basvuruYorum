package com.alibarandemir.isin_asli_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public Map<String, String> uploadProfilePhoto(MultipartFile file) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap(
                "folder", "profile_photos",
                "transformation", new Transformation()
                        .width(400)
                        .height(400)
                        .crop("fill")
                        .gravity("face")
                        .fetchFormat("jpg")
                        .quality("auto")
        );

        Map result = cloudinary.uploader().upload(file.getBytes(), options);

        return Map.of(
                "url", (String) result.get("url"),
                "public_id", (String) result.get("public_id")
        );
    }

    public Map<String, String> uploadFile(MultipartFile file) throws IOException {
        Map<String, String> result = cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap());
        
        return Map.of(
            "url", result.get("url"),
            "public_id", result.get("public_id")
        );
    }

    public void deleteFile(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
} 