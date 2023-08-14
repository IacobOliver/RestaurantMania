package com.codecooll.RestaurantMania.restaurant.service.amazon;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.service.imageUrlService.ImageUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AwsS3Service {

    @Autowired
    private AmazonS3 amazonS3Client;
    @Autowired
    private ImageUrlRepository imageUrlRepository;

    private final String bucketName = "colibri-chase-images";

    public Image uploadImage(MultipartFile multipartFile) throws IOException {

        String uniqueFileName = generateUniqueFileName(multipartFile.getOriginalFilename());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        amazonS3Client.putObject(new PutObjectRequest(bucketName, uniqueFileName, multipartFile.getInputStream(), metadata));
        Image image = new Image();
        image.setImageUrl("https://" + bucketName + ".s3.eu-central-1.amazonaws.com/" + uniqueFileName);
        imageUrlRepository.save(image);
        return image;
    }

    public void deleteImageByUrl (String imageUrl) {

        String key = getImageKeyFromUrl(imageUrl); // Extract the key (filename) from the URL
        System.out.println("key:" + key);
        amazonS3Client.deleteObject(bucketName, key);
    }
    private String getImageKeyFromUrl(String imageUrl) {
        String separator = "/";
        int lastSeparatorIndex = imageUrl.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) {
            throw new IllegalArgumentException("Invalid image URL: " + imageUrl);
        }

        return imageUrl.substring(lastSeparatorIndex + 1);
    }

    private String generateUniqueFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "-" + originalFileName;
    }

}
