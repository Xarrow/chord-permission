package model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import utils.EncipherUtil;

@Data
public class User implements Serializable {

    private Integer id;

    private String uniqueKey;

    private String name;

    private String password;

    private String salt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime = new Date();

    private Integer mark;

    /**
     * 加密
     *
     * @param password
     * @return
     */
    public void setEntryptedPwd(String password) {
        String salt = EncipherUtil.generateSalt();
        this.setSalt(salt);
        this.setPassword(EncipherUtil.encryptedPwd(password, salt));
    }

}
