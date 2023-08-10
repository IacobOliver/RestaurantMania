package com.codecooll.RestaurantMania.restaurant.service.imageUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageUrlService {

    private final ImageUrlRepository imageUrlRepository;

    @Autowired
    public ImageUrlService(ImageUrlRepository imageUrlRepository) {
        this.imageUrlRepository = imageUrlRepository;
    }

}
