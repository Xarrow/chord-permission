package controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

/**
 * Created by wb-zj268791 on 2017/3/29.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String blank() {
        return "redirect:/index";
    }

    @RequestMapping("header")
    public String header() {
        return "head";
    }

    @RequestMapping("index")
    public String index(Model model) {
        //获得当前用户
        Object userKey = SecurityUtils.getSubject().getPrincipal();
        if (null != userKey) {
            Map<String, Object> result = userService.findUserDetailByUserKey(userKey.toString());
            model.addAttribute("userDetail", result);
            return "index";
        } else {
            return "login";
        }
    }

    @RequestMapping("login")
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect: /index";
        } else {
            return "login";
        }
    }

    @RequestMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("logout")
    public String logout() {
        //session.invalidate();
        String userKey = SecurityUtils.getSubject().getPrincipal().toString();
        userService.logOut(userKey);
        SecurityUtils.getSubject().logout();
        return "redirect: /login";

    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String doLogin() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect: /index";
        } else {
            return "login";
        }
    }

    @RequestMapping("manage")
    public String manage(Model model) {
        return "manage";
    }

    @RequestMapping("uafWatch")
    public String uafWatch(Model model) {
        return "uafWatch";
    }


    @RequestMapping("testShiro")
    public String testShiro(Model model){
        return "test";
    }

}
