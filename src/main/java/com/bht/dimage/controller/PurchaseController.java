package com.bht.dimage.controller;


import com.bht.dimage.common.RestResult;
import com.bht.dimage.dto.NewPurchaseDto;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;

@Api(tags = "交易接口")
@RestController
public class PurchaseController {

    @Resource
    PurchaseService purchaseService;

    @ApiOperation(value = "新增交易", notes = "发起交易，保存交易信息")
    @ResponseBody
    @PostMapping(value = "/newPurchase")
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
        long imageID = newPurchaseDto.getImageID();
        if( imageID < 0) {  return RestResult.Fail().message("invalid image ID!");}
        String offer = newPurchaseDto.getOffer();
        if (offer == null || offer.equals("") ) { return RestResult.Fail().message("invalid ether offer!");}
        long launchTime = newPurchaseDto.getLaunchTime();
        if (launchTime < 0 ) { return RestResult.Fail().message("invalid launch time!");}
        long duration = newPurchaseDto.getDuration();
        if (duration < 0 ) { return RestResult.Fail().message("invalid duration!");}

        ptx.setContractAddress(contractAddress);
        ptx.setPurchaser(purchaser);
        ptx.setImageOwner(imageOwner);
        ptx.setImageAuthor(imageAuthor);
        ptx.setImageID(imageID);
        ptx.setOffer(offer);
        ptx.setLaunchTime(launchTime);
        ptx.setDuration(duration);

        return purchaseService.createPurchase(ptx);
    }

    @ApiOperation(value = "获取交易详情", notes = "根据购买者获取交易")
    @ResponseBody
    @GetMapping(value = "/getTxByPurchaser")
    public RestResult getTxByPurchaser(@RequestParam String purchaser) {
        if (purchaser == null || purchaser.equals("")) {
            return RestResult.Fail().message("Invalid Purchaser Address!");
        }
        return purchaseService.fetchTxByPurchaser(purchaser);
    }

    @ApiOperation(value = "获取交易详情", notes = "根据拥有者获取交易")
    @ResponseBody
    @GetMapping(value = "/getTxByOwner")
    public RestResult getTxByOwner(@RequestParam String owner) {
        if (owner == null || owner.equals("")) {
            return RestResult.Fail().message("Invalid Purchaser Address!");
        }
        return purchaseService.fetchTxByOwner(owner);
    }



}
