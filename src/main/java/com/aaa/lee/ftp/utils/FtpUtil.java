package com.aaa.lee.ftp.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/30 12:30
 * @Description
 *      Ftp上传工具类
 **/
public class FtpUtil {

    public static Boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) throws IOException {
        // 1.创建临时路径
        String tempPath = "";
        // 2.FTPClient对象(是ftp进行连接/断开连接和上传的重要对象)
        FTPClient ftp = new FTPClient();
        try {
            // reply:答复(ftp服务器所返回的状态，只会在连接的时候进行返回状态码，上传的时候返回的是boolean类型)
            int reply;
            // 3.connect()：连接ftp
            ftp.connect(host, port);
            // 4.login()：登录ftp的用户
            ftp.login(username, password);
            // 5.ftp.getReplyCode():连接和登录后的状态码
            reply = ftp.getReplyCode(); // reply:230表示连接成功 530表示连接和登录失败
            // 6.判断状态码是否为230(只要是200到300之间[200,300)都返回true，表示连接和登录成功，否则返回false说明登录失败)
            if (!FTPReply.isPositiveCompletion(reply)) {
                // 如果登录失败断开连接
                ftp.disconnect();
                return false;
            }
            // 7.changeWorkingDirectory()：检测目录地址(/home/ftp/www/2019/07/30)是否存在
                // basePath:/home/ftp/www
                // filePath:当前的日期(2019/07/30)
                // /home/ftp/www/2019/07/30
                // 返回Boolean类型，如果返回true则说明目录存在，不需要创建目录，如果返回false则说明目录不存在需要创建
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                // 8.filePath进行分割 dirs = ["2019", "07", "30"]
                String[] dirs = filePath.split("/");
                tempPath = basePath;
                // 9.循环dirs数组
                for (String dir : dirs) {
                    // dir:2019
                    if (null == dir || "".equals(dir))
                        continue; // 跳出当前循环进入下一次循环
                    // tempPath:/home/ftp/www/2019
                    tempPath += "/" + dir;
                    // 10再次检测目标目录是否存在(/home/ftp/www/2019)
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        // makeDirectory()：创建文件夹
                            // 返回值类型为Boolean类型 true:创建成功 false:创建失败
                        if (!ftp.makeDirectory(tempPath)) {
                            return false;
                        } else {
                            // 严谨判断:再次检测创建出的目录是否存在
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            // 11.把文件以字符流的形式进行上传(开启字符流上传的模式)
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // 12.真正的上传其实在storeFile()方法中 返回值为Boolean类型 true:上传成功 false:上传失败
            if (!ftp.storeFile(filename, input)) {
                return false;
            }
            // 13.关闭输入流
            input.close();
            // 14.退出ftp的登录
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    // 断开连接
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return true;
    }


}
