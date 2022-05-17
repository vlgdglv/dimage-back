package com.bht.dimage.controller;


import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.PurchaseDao;
import com.bht.dimage.dto.NewPurchaseDto;
import com.bht.dimage.dto.UpdatePurchaseDto;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.PrevOwnerService;
import com.bht.dimage.service.PurchaseService;
import com.bht.dimage.vo.PurchaseTransactionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "交易接口")
@EnableAsync
@RestController
public class PurchaseController {

    @Resource
    PurchaseService purchaseService;

    @Resource
    PrevOwnerService prevOwnerService;

    @Resource
    PurchaseDao purchaseDao;

    @ApiOperation(value = "新增交易", notes = "发起交易，保存交易信息")
    @ResponseBody
    @PostMapping(value = "/newpurchase")
    public RestResult newPurchase(@RequestBody NewPurchaseDto newPurchaseDto) {
        PurchaseTransaction ptx = new PurchaseTransaction();
        //check parameters
        String contractAddress = newPurchaseDto.getContractAddress();
        if ( contractAddress == null || contractAddress.equals("")){ return RestResult.Fail().message("No Purchase contract address!");}
        String purchaser = newPurchaseDto.getPurchaser();
        if ( purchaser == null || purchaser.equals("")){ return RestResult.Fail().message("No Purchaser address!");}
        String imageOwner = newPurchaseDto.getImageOwner();
        if ( imageOwner == null || imageOwner.equals("")){ return RestResult.Fail().message("No image owner address!");}
        String imageAuthor = newPurchaseDto.getImageAuthor();
        if ( imageAuthor == null || imageAuthor.equals("")){ return RestResult.Fail().message("No image author address!");}
        String authorShare = newPurchaseDto.getAuthorShare();
        String ownerShare = newPurchaseDto.getOwnerShare();
        if ( authorShare == null || authorShare.equals("")){  return RestResult.Fail().message("Invalid author share");}
        if (  ownerShare == null ||  ownerShare.equals("")) { return RestResult.Fail().message("Invalid owner share"); }
        long imageID = newPurchaseDto.getImageID();
        if( imageID < 0) {  return RestResult.Fail().message("invalid image ID!");}
        String sha3 = newPurchaseDto.getSha3();
        if (sha3 == null || sha3.equals("")) { return RestResult.Fail().message("Invalid sha3!");}
        String offer = newPurchaseDto.getOffer();
        if (offer == null || offer.equals("") ) { return RestResult.Fail().message("invalid ether offer!");}
        long launchTime = newPurchaseDto.getLaunchTime();
        if (launchTime < 0 ) { return RestResult.Fail().message("invalid launch time!");}
        long duration = newPurchaseDto.getDuration();
        if (duration < 0 ) { return RestResult.Fail().message("invalid duration!");}

        long endTime = launchTime + duration;

        ptx.setContractAddress(contractAddress.toLowerCase());
        ptx.setPurchaser(purchaser.toLowerCase());
        ptx.setImageOwner(imageOwner.toLowerCase());
        ptx.setImageAuthor(imageAuthor.toLowerCase());
        ptx.setAuthorShare(authorShare);
        ptx.setOwnerShare(ownerShare);
        ptx.setImageID(imageID);
        ptx.setSha3(sha3.toLowerCase());
        ptx.setOffer(offer);
        ptx.setLaunchTime(new Timestamp(launchTime*1000));
        ptx.setEndTime(new Timestamp(endTime*1000));
        ptx.setDuration(duration);

        return purchaseService.createPurchase(ptx);
    }

    @ApiOperation(value = "获取交易详情", notes = "根据拥有者获取交易")
    @ResponseBody
    @GetMapping(value = "/ownertx")
    public RestResult getTxByOwner(@RequestParam String owner,
                                   @RequestParam(defaultValue = "1") int currentPage,
                                   @RequestParam(defaultValue = "5") int pageCount,
                                   @RequestParam(defaultValue = "0") int state) {
        if (owner == null || owner.equals("")) {
            return RestResult.Fail().message("Invalid Purchaser Address!");
        }
        if (pageCount < 0 || currentPage < 0) {
            return RestResult.Fail().message("Invalid page");
        }
        if (state < -3 || state > 3) {
            return RestResult.Fail().message("Invalid state!");
        }
        List<PurchaseTransaction> ptxList= purchaseService.fetchTxByOwner(owner, currentPage, pageCount,state);
        if (ptxList == null) {
            return RestResult.Fail().message("database error!");
        }else {
            int count, totPage;
            if (state != -3) {
                if (state == 0 || state ==2){
                    count = purchaseDao.countByOwner(owner, 0);
                    count +=purchaseDao.countByOwner(owner,2);
                }else{
                    count = purchaseDao.countByOwner(owner, state);
                }
            }else {
                count = purchaseDao.countExpiredByOwner(owner);
            }
            System.out.println("state="+state+", count="+count);
            totPage = (int) Math.ceil((double) count / (double) pageCount);
//            System.out.println(totPage);
            PurchaseTransactionVo ptvo = new PurchaseTransactionVo();
            ptvo.setCurrentPage(currentPage);
            ptvo.setTotalPages(totPage);
            ptvo.setPtxList(ptxList);

            return RestResult.Success().data(ptvo);
        }
    }

    @ApiOperation(value = "获取交易详情", notes = "根据购买者获取交易")
    @ResponseBody
    @GetMapping(value = "/purchasertx")
    public RestResult getTxByPurchaser(@RequestParam String purchaser,
                                       @RequestParam(defaultValue = "1") int currentPage,
                                       @RequestParam(defaultValue = "5") int pageCount,
                                       @RequestParam(defaultValue = "0") int state) {
        if (purchaser == null || purchaser.equals("")) {
            return RestResult.Fail().message("Invalid Purchaser Address!");
        }
        if (pageCount < 0 || currentPage < 0) {
            return RestResult.Fail().message("Invalid page");
        }
        if (state < -3 || state > 3) {
            return RestResult.Fail().message("Invalid state!");
        }
        List<PurchaseTransaction> ptxList= purchaseService.fetchTxByPurchaser(purchaser, currentPage, pageCount,state);
        if (ptxList == null) {
            return RestResult.Fail().message("database error!");
        }else {
            int count, totPage;
            if (state == -3) {
                count = purchaseDao.countExpiredByPurchaser(purchaser);
            }else {
                count = purchaseDao.countByPurchaser(purchaser, state);
            }
            totPage = (int)Math.ceil( (double)count / (double)pageCount );
            System.out.println(count);
            PurchaseTransactionVo ptvo = new PurchaseTransactionVo();
            ptvo.setCurrentPage(currentPage);
            ptvo.setTotalPages(totPage);
            ptvo.setPtxList(ptxList);

            return RestResult.Success().data(ptvo);
        }
    }
    @ApiOperation(value = "获取交易详情", notes = "根据拥有者获取交易")
    @ResponseBody
    @GetMapping(value = "/latestx")
    public RestResult getLatestTx(@RequestParam long imageID) {
        if (imageID < 0) {return RestResult.Fail().message("Invalid ID"); }
        PurchaseTransaction ptx = purchaseDao.getLatestTx(imageID);
        if (ptx == null) {
            return RestResult.Fail().message("No result");
        }else {
            return RestResult.Success().data(ptx);
        }
    }




    @ApiOperation(value = "获取交易详情", notes = "根据拥有者获取交易")
    @ResponseBody
    @PostMapping(value = "/updatetx")
    public RestResult updateTx(@RequestBody UpdatePurchaseDto updatePurchaseDto) {
        String contractAddress = updatePurchaseDto.getContractAddress();
        if (contractAddress == null || contractAddress.equals("")){ return RestResult.Fail().message("No Purchase contract address!"); }
        String from = updatePurchaseDto.getFrom();
        if (from == null || from.equals("")){ return RestResult.Fail().message("No user address"); }
        int oldState = updatePurchaseDto.getOldState();
        int newState = updatePurchaseDto.getNewState();
        if( newState == oldState ) { return RestResult.Fail().message("No state change"); }
        return purchaseService.updateTx(updatePurchaseDto);
    }

    @ApiOperation(value = "获取前拥有者", notes = "根据SHA3获取前拥有者，最多五个")
    @ResponseBody
    @GetMapping(value = "/prevowner")
    public RestResult getPrevOwner(@RequestParam String sha3) {
        if (sha3 == null || sha3.equals("")) { return RestResult.Fail().message("No sha3"); }
        return prevOwnerService.getPrevOwner(sha3);
    }

    @ApiOperation(value = "获得tx", notes = "根据id获得tx")
    @ResponseBody
    @GetMapping(value = "/txbyid")
    public RestResult getTxByID(@RequestParam long txID) {
        System.out.println("txID" + txID);
        if (txID < 0) { return RestResult.Fail().message("Invalid sha3"); }
        return purchaseService.fetchTxByID(txID);
    }

}
