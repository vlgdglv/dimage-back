package com.bht.dimage.vo;

import com.bht.dimage.entity.PurchaseTransaction;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

public class PurchaseTransactionVo {
    private int totalPages;
    private int currentPage;
    private List<PurchaseTransaction> ptxList;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<PurchaseTransaction> getPtxList() {
        return ptxList;
    }

    public void setPtxList(List<PurchaseTransaction> ptxList) {
        this.ptxList = ptxList;
    }
}
