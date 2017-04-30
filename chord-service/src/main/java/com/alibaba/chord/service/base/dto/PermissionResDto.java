package com.alibaba.chord.service.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.alibaba.chord.service.base.model.Permission;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by wb-zj268791 on 2017/4/5.
 */
@Data
public class PermissionResDto extends Permission implements Serializable {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime = new Date();

    private List<Permission> childNodes;

}
