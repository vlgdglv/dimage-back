package com.bht.dimage.dao;


import com.bht.dimage.entity.Image;

import java.util.List;

public interface ImageDao {
    int insertImage(Image image);
    List<Image> selectImageBySHA3(String sha3);
}
