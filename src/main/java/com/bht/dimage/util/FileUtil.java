package com.bht.dimage.util;

import com.bht.dimage.util.PropertiesUtil;

import org.springframework.util.ResourceUtils;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileUtil {
    public static final String[] IMG_FILE = {"bmp", "jpg", "png", "tif", "gif", "jpeg"};
    public static final String[] DOC_FILE = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "txt", "hlp", "wps", "rtf", "html", "pdf"};
    public static final String[] VIDEO_FILE = {"avi", "mp4", "mpg", "mov", "swf"};
    public static final String[] MUSIC_FILE = {"wav", "aif", "au", "mp3", "ram", "wma", "mmf", "amr", "aac", "flac"};
    public static final int IMAGE_TYPE = 1;
    public static final int DOC_TYPE = 2;
    public static final int VIDEO_TYPE = 3;
    public static final int MUSIC_TYPE = 4;
    public static final int OTHER_TYPE = 5;
    public static final int SHARE_FILE = 6;
    public static final int RECYCLE_FILE = 7;
    /**
     * 判断是否为图片文件
     *
     * @param extendName 文件扩展名
     * @return 是否为图片文件
     */
    public static boolean isImageFile(String extendName) {
        for (int i = 0; i < IMG_FILE.length; i++) {
            if (extendName.equalsIgnoreCase(IMG_FILE[i])) {
                return true;
            }
        }
        return false;
    }
    public static String getFileName(String fileName){
        if (!fileName.contains(".")) {
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
    /**
     * 获取文件扩展名
     * @param fileName 文件名
     * @return 文件扩展名
     */
    public static String getFileExtendName(String fileName) {
        if (fileName.lastIndexOf(".") == -1) {
            return "";
            //这里暂时用jpg，后续应该去获取真实的文件类型
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取不包含扩展名的文件名
     *
     * @param fileName 文件名
     * @return 文件名（不带扩展名）
     */
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

    /**
     * 获取项目所在的根目录路径 resources路径
     * @return
     */
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
    /**
     * 路径解码
     * @param url
     * @return
     */
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
