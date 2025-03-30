package com.yorku.green_charge_auto.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Getter
@Setter
@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region}")
    private String region;

    private final S3Client s3Client;


    public S3Service() {
        //System.out.println("REGION IS ===="+region);
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1) // or use Region.of(region)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("AKIA42EKBZVX5DNPYZFT", "8QGtNtKogCDmW78tQp11xcI1nk+CkBv5Mfmwj+6a")
                ))
                .build();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String uniqueFileName = file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(uniqueFileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return uniqueFileName;
    }
}
