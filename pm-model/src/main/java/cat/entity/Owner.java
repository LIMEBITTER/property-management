package cat.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 业主表
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Data
@TableName("tb_owner")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

//    业主ID
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

//    所属小区
    private Integer communityId;

//    成员名称
    private String name;

//    身份证号
    private String idCard;

//    联系方式
    private String telephone;

//    性别:0-男（默认），1-女
    private String sex;

    //创建时间 默认插入
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
//     更新时间 默认插入
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    @ApiModelProperty("出生日期")
    private Date birthday;

    //当前审核状态
    private Integer status;

    //登录用户名
    private String userName;

    //登录密码
    private String password;

    //用户的token
    private String token;

    private String accessToken;

    private Long expiresIn;

    private String refreshToken;

//    private String avatarUrl;


}
