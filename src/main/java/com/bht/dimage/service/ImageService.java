package com.bht.dimage.service;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ImageService {
    RestResult<String> createImage(Image image);
    RestResult selectImageBySHA(String sha);
    String getImageThumbnailPath(long imageID);
    List<Image> getBatchImages(int currentPage, int pageCount,int order);


}
