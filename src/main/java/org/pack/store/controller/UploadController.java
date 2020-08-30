package org.pack.store.controller;

import com.aliyun.oss.OSSClient;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.pack.store.entity.AliyunOssEntity;
import org.pack.store.enums.ResultEnums;
import org.pack.store.service.UserService;
import org.pack.store.utils.AppletResult;
import org.pack.store.utils.ResultUtil;
import org.pack.store.utils.common.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @Autowired
    private UserService userService;

    @CrossOrigin
    @ApiOperation(value = "文件上传")
    @PostMapping(value = "uploadFile",produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "参数",example = "{\"categoryName\":\"\"}")
    public AppletResult uploadFile(@RequestParam MultipartFile file){

        //从数据库获取OSS信息
        AliyunOssEntity aliyunOss = userService.getAliyunOssInfo();

        OSSClient ossClient =new OSSClient(aliyunOss.getEndpoint(), aliyunOss.getAccessKeyId(), aliyunOss.getAccessKeySecret());
        // 1. 对上传的图片进行校验: 这里简单校验后缀名
        // 另外可通过ImageIO读取图片的长宽来判断是否是图片,校验图片的大小等。
        // TODO 图片校验
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                isLegal = true;
                break;  // 只要与允许上传格式其中一个匹配就可以
            }
        }
        // 格式错误, 返回与前端约定的error
        if (!isLegal) {
            return ResultUtil.error(ResultEnums.UPLOAD_IS_ERROR);
        }
        // 2. 准备上传API的参数
        String fileName = file.getOriginalFilename();
        String filePath = this.getFilePath(fileName);

        // 3. 上传至阿里OSS
        try {
            ossClient.putObject(aliyunOss.getBucketName(), filePath, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            // 上传失败
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
        String uploadImg =aliyunOss.getUrlPrefix() + filePath;
        return ResultUtil.success(uploadImg);
    }

    /**
     * 上传的目录
     * 目录: 根据年月日归档
     * 文件名: 时间戳 + 随机数
     * @param fileName
     * @return
     */
    private String getFilePath(String fileName) {
        String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String result="";
        result =
                (time + System.getProperty("file.separator") + UuidUtil.getUuid() + "."+StringUtils.substringAfterLast(fileName, "."))
                        .replace("\\", "/");
        return result;
    }

}
