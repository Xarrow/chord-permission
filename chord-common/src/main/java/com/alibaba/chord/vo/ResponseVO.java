package com.alibaba.chord.vo;

import java.util.Date;

import com.alibaba.chord.enums.ResultCode;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Author wb-zj268791
 * Date 2017/4/25.
 */
@Data
public class ResponseVO<T> {
    private boolean success;

    private ResultCode resultCode;

    private Date time = new Date();

    private T data;

}
