package com.alibaba.chord.controller.base;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.chord.enums.ResultCode;
import com.alibaba.chord.service.base.service.WeiboOauthService;
import com.alibaba.chord.utils.ResponseUtil;
import com.alibaba.chord.vo.ResponseVO;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhang
 * @Time:2017/5/7
 */
@RestController
@RequestMapping("oauth/weibo")
public class ApiOauthController {
    @Resource
    private WeiboOauthService weiboOauthService;

    //@RequestMapping("invoke")
    //public String weiboInvoke(HttpServletRequest request) {
    //    String code = request.getParameter("code");
    //    if (StringUtils.isBlank(code)) {
    //        return "redirect: /login";
    //    }
    //    Subject subject = SecurityUtils.getSubject();
    //    try {
    //        UsernamePasswordToken  oauth2Token = new UsernamePasswordToken(code,"fuck".toCharArray());
    //        subject.login(oauth2Token);
    //        return "redirect: /views/showUser";
    //    } catch (Exception ex) {
    //        return "redirect: /login";
    //    }
    //}

    @RequestMapping("invoke")
    public ResponseVO weiboInvoke(HttpServletRequest request) {
        ResponseVO responseVO = ResponseUtil.buildVoBySuccessResult("");
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {
            responseVO = ResponseUtil.buildVoByResultCode(false, ResultCode.FAILED, "code is null");
            return responseVO;
        }
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("code", code);
        responseVO.setData(weiboOauthService.getAccessToken(null, paramMap));
        return responseVO;
    }

    @RequestMapping("weibo_oauth_login")
    public String weiboOauthLogin() {
        return null;
    }

    @RequestMapping("show")
    public ResponseVO show(HttpServletRequest request) {
        ResponseVO responseVO = ResponseUtil.buildVoBySuccessResult("");
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("uid", request.getParameter("uid"));
        paramMap.put("screen_name", request.getParameter("screen_name"));
        responseVO.setData(weiboOauthService.usersShow(null, paramMap));
        return responseVO;
    }

    /**
     * @param status  status	true	string	要发布的微博文本内容，必须做URLencode，内容不超过140个汉字。
     * @param visible visible	false	int	微博的可见性，0：所有人能看，1：仅自己可见，2：密友可见，3：指定分组可见，默认为0。
     * @return
     */
    @RequestMapping("status/update")
    public ResponseVO statusUpdate(@RequestParam(value = "status", required = false) String status,
                                   @RequestParam(value = "visible", required = false) Integer visible)
        throws UnsupportedEncodingException {
        ResponseVO responseVO = ResponseUtil.buildVoBySuccessResult("");
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("status", StringUtils.isBlank(status) ? new String("你好,世界".getBytes(), "gb2312") : status);
        paramMap.put("visible", visible == null ? 0 : visible);
        responseVO.setData(weiboOauthService.statusUpdate(null, paramMap));
        return responseVO;
    }

    /**
     * access_token	true	string	采用OAuth授权方式为必填参数，OAuth授权后获得。
     * uid	false	int64	需要查询的用户ID。
     * screen_name	false	string	需要查询的用户昵称。
     * count	false	int	单页返回的记录条数，最大不超过100，超过100以100处理，默认为20。
     * page	false	int	返回结果的页码，默认为1。
     * base_app	false	int	是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0。
     * feature	false	int	过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     *
     * @param uid
     * @param screenName
     * @param count
     * @param page
     * @param baseApp
     * @param feature
     * @return
     */
    @RequestMapping("user_timeline")
    public ResponseVO userTimeline(
        @RequestParam(value = "uid", required = false) String uid,
        @RequestParam(value = "screen_name", required = false) String screenName,
        @RequestParam(value = "count", required = false) String count,
        @RequestParam(value = "page", required = false) String page,
        @RequestParam(value = "base_app", required = false) String baseApp,
        @RequestParam(value = "feature", required = false) String feature
    ) {
        ResponseVO responseVO = ResponseUtil.buildVoBySuccessResult("");
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("uid", StringUtils.isBlank(uid) ? "" : uid);
        if (StringUtils.isBlank(uid)) {
            paramMap.put("screen_name", StringUtils.isBlank(screenName) ? "" : screenName);
            paramMap.remove("uid");
        }
        paramMap.put("count", StringUtils.isBlank(count) ? "1" : count);
        paramMap.put("page", StringUtils.isBlank(page) ? "20" : page);
        paramMap.put("base_app", StringUtils.isBlank(baseApp) ? "0" : baseApp);
        paramMap.put("feature", StringUtils.isBlank(feature) ? "0" : feature);
        responseVO.setData(weiboOauthService.userTimeline(null, paramMap));
        return responseVO;
    }

}
