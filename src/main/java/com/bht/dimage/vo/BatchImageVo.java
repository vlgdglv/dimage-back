package com.bht.dimage.vo;

import com.bht.dimage.entity.Image;

import java.util.List;

public class BatchImageVo {
    private int totalPages;
    private int currentPage;
    private List<Image> imageList;


    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

}
