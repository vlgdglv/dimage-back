package com.bht.dimage.service.impl;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.ImageDao;
import com.bht.dimage.entity.Image;
import com.bht.dimage.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageDao imageDao;

    @Override
    public RestResult createImage(Image image) {
        //check if exist
        List<Image> imageList = imageDao.selectImageBySHA3(image.getSha3());
        if ( 0 != imageList.size() ) {
            return RestResult.Fail().message("This image already exists.");
        }
        image.setReleaseTime(new Date(System.currentTimeMillis()));

        if ( 1 == imageDao.insertImage(image)) {
            return RestResult.Success();
        }else {
            return RestResult.Fail().message("Insert failed");
        }
    }

    @Override
    public RestResult selectImageBySHA(String sha3) {
        List<Image> imageList = imageDao.selectImageBySHA3(sha3);
        if (imageList == null) {
            return RestResult.Fail().message("Data base error");
        }else {
            return RestResult.Success().data(imageList);
        }
    }
}
