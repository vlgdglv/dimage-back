package com.bht.dimage.service.impl;

import com.bht.dimage.dao.ImageDao;
import com.bht.dimage.entity.Image;
import com.bht.dimage.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageDao imageDao;

    @Override
    public int createImage(Image image) {
        image.setReleaseTime(new Date(System.currentTimeMillis()));
        return imageDao.insertImage(image);
    }
}
