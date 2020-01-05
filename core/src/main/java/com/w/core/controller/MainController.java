package com.w.core.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/5
 */
@Controller
public class MainController extends BaseController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
//        User user = getLoginUser();
//        if (user == null) {
//            return "redirect:/login";
//        }
//        List<Authorities> authorities = authoritiesService.listByUserId(getLoginUserId());
//        List<Map<String, Object>> menuTree = getMenuTree(authorities, -1);
//        model.addAttribute("menus", menuTree);

        return "index.html";
    }

    /**
     * 验证码
     * @param response
     * @throws IOException
     */
    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(112, 38, 4, 1);
        session.setAttribute("captcha", captcha.getCode());
        captcha.write(response.getOutputStream());
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("login")
    public String login() {
//        if (getLoginUser() != null) {
//            return "redirect:/system";
//        }
        return "login.html";
    }

}
