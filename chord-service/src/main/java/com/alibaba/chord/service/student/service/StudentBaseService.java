package com.alibaba.chord.service.student.service;

import com.alibaba.chord.service.student.model.StudentBaseInfoModel;

/**
 * @Author zhang
 * @Time 2016/8/1.
 */
public interface StudentBaseService {
    /**
     * 根据学号查询
     *
     * @param xh 学号
     * @return student
     */
    StudentBaseInfoModel querySingleStudentByXh(String xh);
}
