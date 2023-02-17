package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.AlibabaOSSFileBiz;
import com.GUFL_kongliang.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @desc：oss控制器
 * @author: 孔量
 * @date：2023/2/17 9:56
 */


@RestController
@RequestMapping("oSS")
public class OssController {

    @Autowired
    private AlibabaOSSFileBiz oSSFileBiz;

    //上传头像到OSS
    @PostMapping("upd")
    public BaseResponse<Object> uploadOssFile(MultipartFile file){
        System.out.println("Upload--------------------------------------------------");
        //获取上传的文件  通过 MultipartFile
        String url = oSSFileBiz.upload(file);//返回上传图片的路径
        return  new BaseResponse<>(200, "成功", url);
    }

}
