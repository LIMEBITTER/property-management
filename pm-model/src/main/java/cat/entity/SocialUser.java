package cat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 第三方登录表（gitee）
 * </p>
 *
 * @author zxb
 * @since 2022-09-20
 */
@Data
//@TableName("tb_social_user")
public class SocialUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty("社交用户id(第三方gitee登录)")
//    @TableId(value = "uid")
    private Integer id;

    //    @ApiModelProperty("登录用户名")
    private String userName;

    //    @ApiModelProperty("密码")
//    private String password;

    private String accessToken;

    //    @ApiModelProperty("token过期时间")
    private Integer expiresIn;

    private String refreshToken;

//    @TableField(exist = false)
//    private String scope;
//
//    @TableField(exist = false)
//    private Long created_at;


}
