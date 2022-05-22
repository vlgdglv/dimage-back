package com.bht.dimage.controller;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.service.ImageService;
import com.bht.dimage.util.FileUtil;
import com.bht.dimage.vo.UploadImageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Api(tags = "上传文件接口")
@RestController
public class UploadController {

    @Resource
    ImageService imageService;

    @ApiOperation(value = "上传文件", notes = "上传缩略图，并以其sha3作为文件名")
    @PostMapping(value = "/uploadImage" )
    @ResponseBody
    public RestResult<String> upload(HttpServletRequest httpServletRequest,
                                     @RequestParam("file") MultipartFile multipartFile,
                                     @RequestParam("sha3") String sha3) {
        String fileName = multipartFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf('.'));
        StringBuilder tempName = new StringBuilder();
        tempName.append(sha3).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile;
        try {
            String dirPath = FileUtil.getFilePath();
            String fullPath = dirPath + newFileName;
            destFile = new File(fullPath);
            multipartFile.transferTo(destFile);
            UploadImageVo uploadImageVo = new UploadImageVo();
            uploadImageVo.setThumbnailPath(fullPath);
            return RestResult.Success().data(uploadImageVo);
        } catch (IOException e) {
            e.printStackTrace();
            return RestResult.Fail().message("Thumbnail fail to upload!");
        }
    }

    @ApiOperation(value = "获取缩略图", notes = "根据路径获取缩略图")
    @PostMapping(value = "/thumbnailbyid")
    public void  getThumbnail(@RequestParam("imageID") long imageID, HttpServletResponse response) {
        String path = imageService.getImageThumbnailPath(imageID);
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int index = 0;
            while((index = fileInputStream.read(buffer))!=-1){
                outputStream.write(buffer,0, index);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "获取缩略图", notes = "根据路径获取缩略图")
    @PostMapping(value = "/thumbnail")
    public void  getThumbnailTest(@RequestParam("path") String path, HttpServletResponse response) {
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int index = 0;
            while((index = fileInputStream.read(buffer))!=-1){
                outputStream.write(buffer,0, index);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
