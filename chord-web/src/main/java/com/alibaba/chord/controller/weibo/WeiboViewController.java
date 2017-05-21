package com.alibaba.chord.controller.weibo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zhang
 * @Time:2017/5/20
 */
@Controller
@RequestMapping("weibo")
public class WeiboViewController {


    @RequestMapping("index")
    public ModelAndView  index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weibo/index");
        return modelAndView;
    }
}
