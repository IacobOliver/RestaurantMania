package com.codecooll.RestaurantMania.controllers;

import com.codecooll.RestaurantMania.services.ImageUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/image")
public class ImageUrlController {
    private ImageUrlService imageUrlService;

    @Autowired
    public ImageUrlController(ImageUrlService imageUrlService) {
        this.imageUrlService = imageUrlService;
    }

}
