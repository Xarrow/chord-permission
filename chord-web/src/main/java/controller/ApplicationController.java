package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 应用Controller
 * Created by wb-zj268791 on 2017/4/6.
 */
@Controller
@RequestMapping("app")
public class ApplicationController {

    @RequestMapping("report")
    public String report(Model model) {
        return "report";
    }

    /**
     * Velocity 模板测试
     *
     * @return
     */
    @RequestMapping("testVm")
    public String testVm() {
        return "jian";
    }
}
