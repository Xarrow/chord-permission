package com.alibaba.chord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhang
 * @Time:2017/5/21
 */

@Controller
@RequestMapping("view")
public class ViewMainController {

    @RequestMapping("main")
    public String main() {

        return "weibo/index";
    }

    @RequestMapping("cslg")
    public String cslg() {
        return "cslg/index";
    }
}
