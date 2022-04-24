package com.bht.dimage.controller;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dto.NewImageDto;
import com.bht.dimage.entity.Image;
import com.bht.dimage.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Api(tags = "图片接口")
@RestController
public class ImageController {

    @Resource
    ImageService imageService;

    @ApiOperation(value = "发布新图片", notes = "发布新图片，保存图片信息")
    @ResponseBody
    @PostMapping(value = "/newImage")
    public RestResult<String> newImage(@RequestBody NewImageDto newImageDto){
        Image image = new Image();
        //check parameters
        if (newImageDto.getImgID() < 0) { return RestResult.Fail().message("Invalid id!"); }
        else{ image.setImageID(newImageDto.getImgID()); }


        if (newImageDto.getAuthor().equals("")) { return RestResult.Fail().message("No author!"); }
        else{
            image.setAuthor(newImageDto.getAuthor());
            image.setOwner(newImageDto.getAuthor());
        }

        if (newImageDto.getHash().equals("")) { return RestResult.Fail().message("No ipfs hash!"); }
        else{ image.setIpfsHash(newImageDto.getHash()); }

        if (newImageDto.getSha3().equals("")) { return RestResult.Fail().message("No sha3!"); }
        else{ image.setSha3(newImageDto.getSha3()); }

        if (newImageDto.getSignature().equals("")) { return RestResult.Fail().message("No signature!"); }
        else{ image.setSignature(newImageDto.getSignature()); }

        if (newImageDto.getTitle().equals("")) { return RestResult.Fail().message("No title!"); }
        else{ image.setTitle(newImageDto.getTitle()); }

        if (newImageDto.getThumbnailPath().equals("")) { return RestResult.Fail().message("No thumbnail path!"); }
        else{ image.setThumbnailPath(newImageDto.getThumbnailPath()); }

        if (imageService.createImage(image) == 1){
            return RestResult.Success().message("create successfully");
        }else{
            return RestResult.Fail().message("Service not available");
        }
    }
}
