package com.alibaba.chord.controller.student;

import javax.annotation.Resource;

import com.alibaba.chord.enums.ResultCode;
import com.alibaba.chord.service.student.model.StudentBaseInfoModel;
import com.alibaba.chord.service.student.service.StudentBaseService;
import com.alibaba.chord.utils.ResponseUtil;
import com.alibaba.chord.vo.ResponseVO;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhang
 * @Time 2016/7/31
 */
@Slf4j
@RestController
@RequestMapping(value = "api/cslg")
public class ApiStudentController {

    @Resource
    private StudentBaseService studentBaseService;

    @RequestMapping(value = "/xh/get/{xh}", method = RequestMethod.GET)
    public ResponseVO querySingleStudentByXh(@PathVariable String xh) {
        ResponseVO vo = ResponseUtil.buildVoByResultCode(false, ResultCode.FAILED);
        if (StringUtils.isBlank(xh)) {
            vo.setData("xh is blank");
            return vo;
        }
        StudentBaseInfoModel studentBaseInfoModel = studentBaseService.querySingleStudentByXh(xh);
        vo = ResponseUtil.buildVoByResultCode(true, ResultCode.SUCCESS, studentBaseInfoModel);
        return vo;
    }

}
