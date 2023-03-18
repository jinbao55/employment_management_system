package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.utils.RedisUtils;
import com.ramostear.captcha.HappyCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @desc：验证码
 * @author: 孔量
 * @date：2023/1/30 20:02
 */
@Service
public class VerificationBiz {

    @Autowired
    RedisUtils redisUtils;



    /**
     * @Desc: 清理验证码
     * @Auther: 孔量
     * @Date: 2023/1/30 20:00
     * @param: request
     * @Return: void
     */
    public void removeCaptcha(HttpServletRequest request) {
        HappyCaptcha.remove(request);
    }


    /**
     * @Desc: 验证
     * @Auther: 孔量
     * @Date: 2023/1/30 20:00
     * @param: code
     * @param: request
     * @Return: String
     */
    public boolean verify(String code, HttpServletRequest request) {

        return HappyCaptcha.verification(request, code, true);

    }


    /**
     * @Desc:  异步操作Redis
     * @Auther: 孔量
     * @Date: 2023/3/18 8:41
     * @param: code
     * @Return: void
     */
    @Async
    public void redisOperate(String code){
        redisUtils.deleteKey("code");
        redisUtils.setValue("code",code,2, TimeUnit.MINUTES);
    }

}
