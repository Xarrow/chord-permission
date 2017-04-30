package com.alibaba.chord.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.chord.enums.ResultCode;
import com.alibaba.chord.vo.ResponseVO;

/**
 * Author wb-zj268791
 * Date 2017/4/25.
 */
public class ResponseUtil {

    private ResponseUtil() {}

    public static ResponseVO buildVoByResultCode(Boolean success, ResultCode resultCode) {
        return buildVoByResultCode(success, resultCode, null);
    }

    public static <T> ResponseVO buildVoBySuccessResult(Boolean success, T data) {
        return buildVoByResultCode(success, ResultCode.SUCCESS, data);
    }

    public static <T> ResponseVO buildVoByResultCode(
        boolean success, ResultCode resultCode, T data) {

        ResponseVO<Object> vo = new ResponseVO<Object>();
        if (data instanceof Map) {
            vo.setData(data);
        } else if (data instanceof List) {
            List list = ((List)data);
            vo.setData(getListJsonData(list));
        } else {
            vo.setData(data);
        }
        vo.setSuccess(success);
        vo.setResultCode(resultCode);
        return vo;
    }

    /**
     * List×ªMap¶ÔÏó
     *
     * @param list
     * @return
     */
    private static Map<String, Object> getListJsonData(List list) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != list && list.size() > 0) {
            map.put("count", list.size());
            map.put("rows", list);
        }
        return map;
    }

}
