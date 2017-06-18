package com.alibaba.chord.controller;

import com.alibaba.chord.utils.ResponseUtil;
import com.alibaba.chord.vo.ResponseVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhang
 * @Time:2017/6/18
 */
@RestController
@RequestMapping("nexus")
public class NexusController {
    @RequestMapping("testShiro")
    public ResponseVO testShiro() {
        ResponseVO responseVO = ResponseUtil.buildVoBySuccessResult("你好");


        return responseVO;

    }

}
