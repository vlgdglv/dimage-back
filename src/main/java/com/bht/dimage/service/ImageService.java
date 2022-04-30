package com.bht.dimage.service;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.entity.Image;
import org.springframework.stereotype.Service;

public interface ImageService {
    RestResult<String> createImage(Image image);
    RestResult selectImageBySHA(String sha);
}
