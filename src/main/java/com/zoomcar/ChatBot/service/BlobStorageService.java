package com.zoomcar.ChatBot.service;


import com.zoomcar.ChatBot.configuration.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class BlobStorageService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties minioProperties;

    public List<Bucket> listBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to list buckets", e);
        }
    }

    public String uploadFile(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucketName())
                .stream(inputStream, inputStream.available(), -1)
                .contentType("application/octet-stream")
                .build()
        );
        System.out.println("File uploaded successfully.");
        return "";
    }





}
