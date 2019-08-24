package com.aaa.lee.ftp.utils;

import java.util.Random;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/30 15:03
 * @Description
 *      文件名工具类
 **/
public class FileNameUtil {

    public static String getFileName() {
        // 1.获取当前系统的时间毫秒数
        long millis = System.currentTimeMillis();
        System.out.println(millis);
        // 2.生成随机数(0-999之间进行随机)
        Random random = new Random();
        int randomNum = random.nextInt(999);
        // 3.需要进行占位符(需要把当前系统时间的毫秒数和随机数整合在一起)
        // %:占位符   03:三位(如果不足三位往前补0)  d:数字
        String fileName = millis + String.format("%03d", randomNum);
        // 4.返回文件名称
        return fileName;
    }

    public static void main(String[] args) {
        System.out.println(FileNameUtil.getFileName());
    }

}
