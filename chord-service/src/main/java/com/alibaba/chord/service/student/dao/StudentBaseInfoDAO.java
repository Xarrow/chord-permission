package com.alibaba.chord.service.student.dao;

import java.util.List;

import com.alibaba.chord.service.student.model.StudentBaseInfoModel;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentBaseInfoDAO {

    /**
     * 插入数据
     *
     * @param record
     */
    void insertSelective(StudentBaseInfoModel record);

    /**
     * 通过学号查询
     *
     * @param xh
     * @return
     */
    StudentBaseInfoModel selectByXH(String xh);

    /**
     * 通过姓名查询
     *
     * @param xm
     * @return
     */
    List<StudentBaseInfoModel> selectByXm(String xm);

}