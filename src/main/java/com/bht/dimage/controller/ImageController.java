package com.bht.dimage.controller;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dto.NewImageDto;
import com.bht.dimage.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ImageController {

//    @Resource
//    ImageService imageService;

    @ResponseBody
    @PostMapping(value = "/newImage")
    public RestResult<String> newImage(@RequestBody NewImageDto newImageDto){
        System.out.println(newImageDto.getAuthor());
        System.out.println(newImageDto.getHash());
        System.out.println(newImageDto.getSha3());
        System.out.println(newImageDto.getSignature());
        System.out.println(newImageDto.getTitle());
        return RestResult.Fail();
    }
}
