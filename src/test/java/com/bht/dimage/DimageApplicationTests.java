package com.bht.dimage;

import com.bht.dimage.common.RestResult;
import com.bht.dimage.dao.ImageDao;
import com.bht.dimage.dao.PurchaseDao;
import com.bht.dimage.entity.Image;
import com.bht.dimage.entity.PurchaseTransaction;
import com.bht.dimage.service.ImageService;
import com.bht.dimage.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

//Test classes
import com.bht.dimage.util.FileUtil;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class DimageApplicationTests {

	@Resource
	ImageDao imageDao;
	@Test
	void contextLoads() {
	}

	@Test
	void FileUtilTest() {
		System.out.println("FilePath:");
		System.out.println(FileUtil.getFilePath());
	}

	@Resource
	PurchaseService purchaseService;

	@Test
	void PurchaseInsertTest() {
		PurchaseTransaction ptx = new PurchaseTransaction();
		RestResult rest = null;
		//normal
		ptx.setContractAddress("0x1234567890");
		ptx.setPurchaser("purchaser");
		ptx.setImageOwner("owner");
		ptx.setImageAuthor("author");
		ptx.setImageID(1);
		ptx.setOffer("100000000000000");
		ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
		ptx.setEndTime(new Timestamp(System.currentTimeMillis()));
		ptx.setDuration(360);
		rest = purchaseService.createPurchase(ptx);
		System.out.println("insert #1:" + rest.getMessage());
		//normal
		ptx.setContractAddress("0x0987654321");
		ptx.setPurchaser("purchaser2");
		ptx.setImageOwner("owner348");
		ptx.setImageAuthor("author6902");
		ptx.setImageID(4);
		ptx.setOffer("10000000000000088888");
		ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
		ptx.setEndTime(new Timestamp(System.currentTimeMillis()));
		ptx.setDuration(1000);
		rest  = purchaseService.createPurchase(ptx);
		System.out.println("insert #2:" + rest.getMessage());
		//normal
		ptx.setContractAddress("0x1DS890");
		ptx.setPurchaser("purchaser");
		ptx.setImageOwner("ownerAFS");
		ptx.setImageAuthor("author");
		ptx.setImageID(84);
		ptx.setOffer("100000000000000");
		ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
		ptx.setEndTime(new Timestamp(System.currentTimeMillis()));
		ptx.setDuration(360);
		rest  = purchaseService.createPurchase(ptx);
		System.out.println("insert #3:" + rest.getMessage());
		//normal
		ptx.setContractAddress("0x678695403S890");
		ptx.setPurchaser("nsfw");
		ptx.setImageOwner("ownerAFS");
		ptx.setImageAuthor("author6902");
		ptx.setImageID(56);
		ptx.setOffer("100000000000000");
		ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
		ptx.setEndTime(new Timestamp(System.currentTimeMillis()));
		ptx.setDuration(360);
		rest  = purchaseService.createPurchase(ptx);
		System.out.println("insert #4:" + rest.getMessage());
		//double purchase
		ptx.setContractAddress("0x59402389345640");
		ptx.setPurchaser("purchaser");
		ptx.setImageOwner("owner");
		ptx.setImageAuthor("author");
		ptx.setImageID(1);
		ptx.setOffer("8888888000000000");
		ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
		ptx.setEndTime(new Timestamp(System.currentTimeMillis()));
		ptx.setDuration(12960);
		rest  = purchaseService.createPurchase(ptx);
		System.out.println("insert #5:" + rest.getMessage());
		//contract exists
		ptx.setContractAddress("0x678695403S890");
		ptx.setPurchaser("pu3");
		ptx.setImageOwner("owner");
		ptx.setImageAuthor("author");
		ptx.setImageID(1);
		ptx.setOffer("8888888000000000");
		ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
		ptx.setEndTime(new Timestamp(System.currentTimeMillis()));
		ptx.setDuration(12960);
		rest  = purchaseService.createPurchase(ptx);
		System.out.println("insert #6:" + rest.getMessage());
	}

	@Resource
	PurchaseDao purchaseDao;

	@Test
	void purchaseSelectTest() {
		List<PurchaseTransaction> ptxList = null;

		ptxList = purchaseDao.selectByPurchaser("purchaser4",1,5,1);
		System.out.println("null?" + ptxList == null);
		System.out.println("By purchase:" + ptxList.size());
		for( PurchaseTransaction ptx: ptxList){
			System.out.println("ptx contract address=" + ptx.getContractAddress());
//			System.out.println(ptx.getPurchaser());
//			System.out.println(ptx.getImageOwner());
//			System.out.println(ptx.getImageAuthor());
//			System.out.println(ptx.getImageID());
//			System.out.println(ptx.getOffer());
//			System.out.println(ptx.getLaunchTime());
//			System.out.println(ptx.getDuration());
//			System.out.println(ptx.getIsClosed());
//			System.out.println(ptx.getState());
		}

		ptxList = purchaseDao.selectByImageOwner("ownerAFS",1,5,0);
		System.out.println("By owner:" + ptxList.size());
		for( PurchaseTransaction ptx: ptxList){
			System.out.println("ptx contract address=" + ptx.getContractAddress());
//			System.out.println(ptx.getPurchaser());
//			System.out.println(ptx.getImageOwner());
//			System.out.println(ptx.getImageAuthor());
//			System.out.println(ptx.getImageID());
//			System.out.println(ptx.getOffer());
//			System.out.println(ptx.getLaunchTime());
//			System.out.println(ptx.getDuration());
//			System.out.println(ptx.getIsClosed());
//			System.out.println(ptx.getState());
		}
	}

	@Test
	void purchaseDaoTest() {
		List<PurchaseTransaction> ptxList = purchaseDao.selectExpiredByOwner("own",0,7);
		for(PurchaseTransaction ptx : ptxList) {
			System.out.println(ptx.getContractAddress());
		}
	}

	@Test
	void batchInsertPurchase() {
		PurchaseTransaction ptx = new PurchaseTransaction();
		long start = System.currentTimeMillis();
		for (int i=0; i< 20;i++) {
//			ptx.setContractAddress("0xaaaaaaaaaaaaaaaa" + i);
			ptx.setContractAddress("0x1234567890" + i);
//			ptx.setPurchaser("0x9aEB35aa6EE18cDe040E3903B6aec935619D75cB");
			ptx.setPurchaser("pur"+ i);
//			ptx.setImageOwner("own" + i);
			ptx.setImageOwner("0x9aEB35aa6EE18cDe040E3903B6aec935619D75cB" );
//			ptx.setImageID(i+20);
			ptx.setImageID(i);
			ptx.setOwnerShare("65000000000000000");
			ptx.setAuthorShare("20000000000000000");
			ptx.setImageAuthor("author"+ i);
			ptx.setSha3("sha3"+i);
			ptx.setOffer("100000000000000000");
			ptx.setLaunchTime(new Timestamp(System.currentTimeMillis()));
			ptx.setEndTime(new Timestamp(System.currentTimeMillis()+18000000));
			ptx.setDuration(36000);
			ptx.setIsClosed(0);
			ptx.setState(1);
			ptx.setPrevOwnerShareRatio(-1);
			purchaseDao.insertPurchase(ptx);
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println("Time used = "+ cost + "ms");
	}

	@Test
	void batchInsertImage() {
		Image image = new Image();
		long start = System.currentTimeMillis();
		for (int i=0; i< 20;i++) {
			image.setImageID(i+20);
//			image.setImageID(i);
			image.setOwner("own" + i);
//			image.setOwner("0x9aEB35aa6EE18cDe040E3903B6aec935619D75cB");
			image.setAuthor	("author"+ i);
			image.setSha3("sha3"+i);
			image.setSignature("NM$L" + i);
			image.setTxCount(0);
			image.setTitle("Holy s");
			image.setReleaseTime(new Timestamp(System.currentTimeMillis()));
			image.setIpfsHash("IPFS HASH");
			image.setThumbnailPath("Path");
			imageDao.insertImage(image);
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println("Time used = "+ cost + "ms");
	}
}
