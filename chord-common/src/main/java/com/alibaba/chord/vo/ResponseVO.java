package com.alibaba.chord.vo;

import com.alibaba.chord.enums.ResultCode;

import lombok.Data;

/**
 * Author wb-zj268791
 * Date 2017/4/25.
 */
@Data
public class ResponseVO<T> {
    private boolean success;

    private ResultCode resultCode;

    private T data;

}
