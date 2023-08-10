package com.codecooll.RestaurantMania.restaurant.service.imageUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/image")
@CrossOrigin(origins = "http://localhost:5173/")
public class ImageUrlController {
    private ImageUrlService imageUrlService;

    @Autowired
    public ImageUrlController(ImageUrlService imageUrlService) {
        this.imageUrlService = imageUrlService;
    }

}
