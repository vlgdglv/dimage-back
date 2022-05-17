package com.bht.dimage.controller;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.ImageDao;
import com.bht.dimage.dto.NewImageDto;
import com.bht.dimage.entity.Image;
import com.bht.dimage.service.ImageService;
import com.bht.dimage.vo.BatchImageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api(tags = "图片接口")
@RestController
public class ImageController {

    @Resource
    ImageService imageService;
    @Resource
    ImageDao imageDao;

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
        image.setAuthor(author.toLowerCase());
        image.setOwner(author.toLowerCase());
        image.setSha3(sha3.toLowerCase());
        image.setSignature(signature.toLowerCase());
        image.setTitle(title);
        image.setThumbnailPath(thumbnailPath);

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
    @ApiOperation(value = "查找图片", notes = "根据ID，查找图片信息")
    @ResponseBody
    @GetMapping(value = "/getimagebyid")
    public RestResult getImageByID(@RequestParam int id) {
        if ( id < 0) { return RestResult.Fail().message("Invalid sid!"); }
        Image image = imageDao.selectImageByID(id).get(0);
        if (image == null) {
            return RestResult.Fail().message("data base error!");
        }else {
            return RestResult.Success().data(image);
        }
    }

    @ApiOperation(value = "查找图片", notes = "根据作者，查找图片信息")
    @ResponseBody
    @GetMapping(value = "/getimagebyauthor")
    public RestResult getImageByAuthor(@RequestParam String author,
                                       @RequestParam(defaultValue = "1") int currentPage,
                                       @RequestParam(defaultValue = "16") int pageCount,
                                       @RequestParam(defaultValue = "0") int order) {
        if ( author == null || author.equals("")) { return RestResult.Fail().message("Invalid author!"); }
        int begin = (currentPage - 1 ) * pageCount;
        List<Image> imageList = imageDao.selectImageByAuthor(author, begin, pageCount, order);
        int count = imageDao.countByAuthor(author);
        int totalPage = (int) Math.ceil((double) count / (double)pageCount);
        BatchImageVo bivo = new BatchImageVo();
        bivo.setImageList(imageList);
        bivo.setCurrentPage(currentPage);
        bivo.setTotalPages(totalPage);
        return RestResult.Success().data(bivo);
    }

    @ApiOperation(value = "查找图片", notes = "根据作者，查找图片信息")
    @ResponseBody
    @GetMapping(value = "/getimagebyowner")
    public RestResult getImageByOwner(@RequestParam String owner,
                                       @RequestParam(defaultValue = "1") int currentPage,
                                       @RequestParam(defaultValue = "16") int pageCount,
                                       @RequestParam(defaultValue = "0") int order) {
        if ( owner == null || owner.equals("")) { return RestResult.Fail().message("Invalid author!"); }
        int begin = (currentPage - 1 ) * pageCount;
        List<Image> imageList = imageDao.selectImageByOwner(owner, begin, pageCount, order);
        int count = imageDao.countByOwner(owner);
        int totalPage = (int) Math.ceil((double) count / (double)pageCount);
        BatchImageVo bivo = new BatchImageVo();
        bivo.setImageList(imageList);
        bivo.setCurrentPage(currentPage);
        bivo.setTotalPages(totalPage);
        return RestResult.Success().data(bivo);
    }

    @ApiOperation(value = "", notes = "")
    @ResponseBody
    @GetMapping(value = "/getimages")
    public RestResult getImages(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "16") int pageCount,
                                @RequestParam(defaultValue = "0") int order) {
        if (currentPage < 0 || pageCount < 0 ) {
            return RestResult.Fail().message("Invalid page parameters");
        }
        List<Image> imageList = imageService.getBatchImages(currentPage, pageCount, order);
        int count = imageDao.countImages();
        int totalPage = (int) Math.ceil((double) count / (double)pageCount);
        BatchImageVo bivo = new BatchImageVo();
        bivo.setImageList(imageList);
        bivo.setCurrentPage(currentPage);
        bivo.setTotalPages(totalPage);
        return RestResult.Success().data(bivo);
    }

}
