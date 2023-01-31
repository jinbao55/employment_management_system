package com.GUFL_kongliang.biz;

import com.ramostear.captcha.HappyCaptcha;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc：验证码
 * @author: 孔量
 * @date：2023/1/30 20:02
 */
@Service
public class VerificationBiz {

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
}
