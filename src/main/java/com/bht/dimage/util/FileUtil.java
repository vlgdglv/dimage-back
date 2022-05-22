package com.bht.dimage.util;

import org.springframework.util.ResourceUtils;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileUtil {

    public static String getFileName(String fileName){
        if (!fileName.contains(".")) {
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static String getFileExtendName(String fileName) {
        if (fileName.lastIndexOf(".") == -1) {
            return "";
            //这里暂时用jpg，后续应该去获取真实的文件类型
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getFileNameNotExtend(String fileName) {
        String fileType = getFileExtendName(fileName);
        return fileName.replace("." + fileType, "");
    }

    public static String getFilePath() {
        String path ="";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        path = File.separator + format.format(new Date());
        String staticPath = FileUtil.getProjectRootPath()+FileUtil.getStaticPath();
        File dir = new File(staticPath + path);
//        System.out.println("staticPath:"+staticPath);
        if (!dir.exists()){
            try{
                boolean flag =  dir.mkdirs();
                if(!flag) {
                    return "";
                }
            } catch (Exception e){
                e.printStackTrace();
                return "";
            }
        }
//        System.out.println("Full:" + staticPath + path);
        return staticPath+path + File.separator;
    }

    public static String getStaticPath() {
        String localStoragePath = PropertiesUtil.getProperty("file.local-storage-path");
        if (localStoragePath!=null && !localStoragePath.equals("")) {
            return localStoragePath;
        }else{
            String rootPath = FileUtil.getProjectRootPath();
            int index = rootPath.lastIndexOf("file:");
            if (index != -1){
                rootPath = rootPath.substring(index);
            }
            return rootPath + "static" + File.separator;
        }
    }

    public static String getProjectRootPath() {
        String absolutePath = null;
        try {
            String url = ResourceUtils.getURL("classpath:").getPath();
            absolutePath = urlDecode(new File(url).getAbsolutePath()) + File.separator;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index = absolutePath.lastIndexOf("file:");
        if (index != -1){
            absolutePath = absolutePath.substring(0,index);
        }
        return absolutePath;
    }

    public static String urlDecode(String url){
        String decodeUrl = null;
        try {
            decodeUrl = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  decodeUrl;
    }
}
