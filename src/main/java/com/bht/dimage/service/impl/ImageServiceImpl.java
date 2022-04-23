package com.bht.dimage.service.impl;

import com.bht.dimage.dao.ImageDao;
import com.bht.dimage.entity.Image;
import com.bht.dimage.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageDao imageDao;

    @Override
    public int createImage(Image image) {
//        return imageDao.insertImage(image);
        return 0;
    }
}
