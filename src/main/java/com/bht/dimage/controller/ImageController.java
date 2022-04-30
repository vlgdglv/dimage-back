package com.bht.dimage.controller;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dto.NewImageDto;
import com.bht.dimage.entity.Image;
import com.bht.dimage.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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
        long imageID = newImageDto.getImgID();
        if ( imageID < 0) { return RestResult.Fail().message("Invalid id!"); }
        String author = newImageDto.getAuthor();
        if ( author == null || author.equals("")) { return RestResult.Fail().message("No author!"); }
        String ipfsHash = newImageDto.getHash();
        if ( ipfsHash == null || ipfsHash.equals("")) { return RestResult.Fail().message("No ipfs hash!"); }
        String sha3 = newImageDto.getSha3();
        if ( sha3 == null || sha3.equals("")) { return RestResult.Fail().message("No sha3!"); }
        String signature = newImageDto.getSignature();
        if ( signature == null || signature.equals("")) { return RestResult.Fail().message("No signature!"); }
        String title = newImageDto.getTitle();
        if ( title == null || title.equals("")) { return RestResult.Fail().message("No title!"); }
        String thumbnailPath = newImageDto.getThumbnailPath();
        if ( thumbnailPath == null || thumbnailPath.equals("")) { return RestResult.Fail().message("No thumbnail path!"); }
        //pack up image entity
        image.setImageID(imageID);
        image.setAuthor(author);
        image.setOwner(author);
        image.setIpfsHash(ipfsHash);
        image.setSha3(sha3);
        image.setSignature(signature);
        image.setTitle(title);
        image.setThumbnailPath(thumbnailPath);

//        return RestResult.Fail().message("test fail");
        RestResult<String> createResult = imageService.createImage(image);
        if (createResult.isSuccess()){
            return RestResult.Success().message("create successfully");
        }else{
            return createResult;
        }
    }

    @ApiOperation(value = "查找图片", notes = "根据sha3，查找图片信息")
    @ResponseBody
    @GetMapping(value = "/getImagebysha")
    public RestResult getImageBySHA(@RequestParam String sha3) {
        if ( sha3 == null || sha3.equals("")) { return RestResult.Fail().message("No sha3!"); }
        return imageService.selectImageBySHA(sha3);
    }

}
