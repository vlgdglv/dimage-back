package com.bht.dimage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//Test classes
import com.bht.dimage.util.FileUtil;

@SpringBootTest
class DimageApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void FileUtilTest() {
		System.out.println("FilePath:");
		System.out.println(FileUtil.getFilePath());
	}
}
