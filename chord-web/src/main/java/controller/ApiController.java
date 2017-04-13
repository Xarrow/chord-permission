package controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CacheService;
import service.UserService;

/**
 * Created by wb-zj268791 on 2017/3/29.
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @Resource
    private CacheService cacheService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "flushCache", method = RequestMethod.GET)
    public JSONObject flushCache(@RequestParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", cacheService.flushCacheByName(name));
        return jsonObject;
    }

    @RequestMapping(value = "flushAllCache")
    public JSONObject flushAllCache() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", cacheService.flushAllCache());
        return jsonObject;
    }

    @RequestMapping(value = "showCache")
    public JSONObject showCache(@RequestParam("name") String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("data", cacheService.showCache(name));
        return jsonObject;
    }

    @RequiresRoles("sale")
    @RequestMapping(value = "showAllCache")
    public JSONObject showAllCache() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", cacheService.showAllCache());
        return jsonObject;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public JSONObject addUser(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "password", required = true) String password) {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        user.setName(name);
        user.setEntryptedPwd(password);
        int resultCode = userService.addUser(user);
        jsonObject.put("resultCode", resultCode);
        return jsonObject;
    }

    @RequestMapping(value = "checkSession", method = RequestMethod.POST)
    public JSONObject checkSession() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isExpired", !SecurityUtils.getSubject().isAuthenticated());
        return jsonObject;
    }
}
