package com.codecooll.RestaurantMania.restaurant.service.cloudStorage;

import com.codecooll.RestaurantMania.restaurant.model.Image;
import com.codecooll.RestaurantMania.restaurant.service.imageUrlService.ImageUrlRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    private ImageUrlRepository imageUrlRepository;
    private final String BUCKET_NAME = "restaurant-mania"; // Replace with your bucket name
    private final String CREDENTIALS_PATH = "P:\\Codecool\\Modul4Java\\RestaurantMania\\Backend\\src\\main\\resources\\linear-range-375117-a874aad140b4.json"; // Replace with the actual path

    private final Storage storage = StorageOptions.newBuilder()
            .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
            .build()
            .getService();

    public ImageService() throws IOException {
    }

    public Image uploadImage(MultipartFile file) throws IOException {
//        Storage storage = StorageOptions.newBuilder()
//                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
//                .build()
//                .getService();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        storage.create(blobInfo, file.getBytes());

        Image image = new Image();
        image.setImageUrl("https://storage.googleapis.com/" + BUCKET_NAME + "/" + fileName);
        imageUrlRepository.save(image);
        return image;
    }

    public boolean deleteImageByUrl(String imageUrl) {

        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

        BlobId blobId = BlobId.of(BUCKET_NAME, imageName);

        boolean deleted = storage.delete(blobId);

        if (deleted) {
            System.out.println("Image '" + imageName + "' deleted successfully.");
        } else {
            System.out.println("Image '" + imageName + "' deletion failed.");
        }

        return false;
    }
}
