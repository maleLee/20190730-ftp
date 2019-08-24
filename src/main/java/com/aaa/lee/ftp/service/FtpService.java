package com.aaa.lee.ftp.service;

import com.aaa.lee.ftp.config.FtpPropertiesConfig;
import com.aaa.lee.ftp.utils.FileNameUtil;
import com.aaa.lee.ftp.utils.FtpUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/30 14:59
 * @Description
 *      Ftp的service层
 *      对ftp所有的业务逻辑进行操作
 *      上传
 *      下载
 **/
@Service
public class FtpService {

    @Autowired
    private FtpPropertiesConfig ftpPropertiesConfig;

    /**
     * @author Seven Lee
     * @description
     *      上传方法
     * @param file
     * @date 2019/7/30
     * @return java.lang.Boolean
     * @throws
     **/
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1.获取文件的原始名称
        String oldFileName = file.getOriginalFilename();
        // 2.创建新的文件名称
        String newFileName = FileNameUtil.getFileName();
        // 3.截取原始名称的后缀
        String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
        // 4.把截取出的后缀拼接到新的文件名中
        newFileName = newFileName + substring;
        // 5.创建文件目标的目录
        String filePath = new DateTime().toString("yyyy/MM/dd");// 2019/7/30
        // 6.使用工具类进行连接Ftp和上传功能
            // 如何获取输入流？  file.getInputStream();
            // 所有的异常信息尽可能的精确！！！！
        try {
            Boolean uploadResult = FtpUtil.uploadFile(ftpPropertiesConfig.getHost(), Integer.parseInt(ftpPropertiesConfig.getPort()),
                    ftpPropertiesConfig.getUsername(), ftpPropertiesConfig.getPassword(), ftpPropertiesConfig.getBasePath(),
                    filePath, newFileName, file.getInputStream());
            String picPath = ftpPropertiesConfig.getHttpPath()+"/"+filePath+"/"+newFileName;
            resultMap.put("result", uploadResult);
            resultMap.put("picPath", picPath);
        } catch (IOException e) {
            resultMap.put("result", false);
            e.printStackTrace();
        }
        return resultMap;
    }

}
