package com.bht.dimage.controller;


import com.bht.dimage.common.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "交易接口")
@RestController
public class PurchaseController {

    @ApiOperation(value = "新增交易", notes = "发起交易，保存交易信息")
    @ResponseBody
    @PostMapping(value = "/newPurchase")
    public RestResult newPurchase() {

        return RestResult.Fail();
    }

}
