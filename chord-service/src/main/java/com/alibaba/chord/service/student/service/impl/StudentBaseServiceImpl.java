package com.alibaba.chord.service.student.service.impl;

import javax.annotation.Resource;

import com.alibaba.chord.commons.CommonsValue;
import com.alibaba.chord.service.student.dao.StudentBaseInfoDAO;
import com.alibaba.chord.service.student.model.StudentBaseInfoModel;
import com.alibaba.chord.service.student.service.StudentBaseService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * 学生信息基本服务
 *
 * @author zhang jian
 * @Time 2016/7/31.
 */
@Slf4j
@EnableCaching(proxyTargetClass = true)
@Service
public class StudentBaseServiceImpl implements StudentBaseService {

    @Resource
    private StudentBaseInfoDAO studentBaseInfoDAO;

    /**
     * 根据学号查询
     *
     * @param xh 学号
     * @return student
     */
    @Cacheable(value = CommonsValue.STU_QUERY_CACHENAME, condition = "#xh!=''", key = "#xh")
    @Override
    public StudentBaseInfoModel querySingleStudentByXh(String xh) {
        StudentBaseInfoModel studentBaseInfoModel = null;
        try {
            studentBaseInfoModel = studentBaseInfoDAO.selectByXH(xh);
        } catch (Exception ex) {
            log.info("根据学号查询用户异常:[{}]", ex);
            return null;
        }
        return studentBaseInfoModel;
    }
}
