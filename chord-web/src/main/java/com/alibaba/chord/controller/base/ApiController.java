package com.alibaba.chord.controller.base;

import javax.annotation.Resource;

import com.alibaba.chord.enums.ResultCode;
import com.alibaba.chord.utils.ResponseUtil;
import com.alibaba.chord.vo.ResponseVO;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.chord.service.base.model.User;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.chord.service.base.service.CacheService;
import com.alibaba.chord.service.base.service.UserService;

/**
 * Created by wb-zj268791 on 2017/3/29.
 */
@RestController
@RequestMapping("api/auth")
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
    public ResponseVO showAllCache() {
        ResponseVO responseVO = ResponseUtil.buildVoByResultCode(false, ResultCode.API_CONNECT_FAILED);
        try {
            responseVO = ResponseUtil.buildVoByResultCode(true, ResultCode.SUCCESS, cacheService.showAllCache());
        } catch (Exception ex) {
            responseVO = ResponseUtil.buildVoByResultCode(false, ResultCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
        return responseVO;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public ResponseVO addUser(@RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "password", required = true) String password) {
        ResponseVO responseVO = ResponseUtil.buildVoByResultCode(false, ResultCode.API_CONNECT_FAILED);
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
            JSONObject jsonObject = new JSONObject();
            User user = new User();
            user.setName(name);
            user.setUniqueKey(name);
            user.setEntryptedPwd(password);
            int resultCode = userService.addUser(user);
            jsonObject.put("resultCode", resultCode);
            responseVO = ResponseUtil.buildVoByResultCode(true, ResultCode.SUCCESS, jsonObject);
        } else {
            responseVO = ResponseUtil.buildVoByResultCode(false, ResultCode.PARAMETER_ERROR, "");
        }
        return responseVO;
    }

    @RequestMapping(value = "checkSession", method = RequestMethod.POST)
    public JSONObject checkSession() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isExpired", !SecurityUtils.getSubject().isAuthenticated());
        return jsonObject;
    }
}
