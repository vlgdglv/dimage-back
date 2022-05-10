package com.bht.dimage.dao;


import com.bht.dimage.entity.Image;

import java.util.List;

public interface ImageDao {
    int insertImage(Image image);
    List<Image> selectImageBySHA3(String sha3);
    List<Image> selectImageByID(long imageID);
    int updateImage(Image image);
    int countImages();
    List<Image> selectImages(int begin, int count, int order);
    List<Image> selectImageByAuthor(String author,int begin, int count, int order);
    int countByAuthor(String author);
    List<Image> selectImageByOwner(String owner,int begin, int count, int order);
    int countByOwner(String owner);
}
