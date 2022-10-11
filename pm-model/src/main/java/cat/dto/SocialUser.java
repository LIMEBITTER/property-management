package cat.dto;

import cat.entity.Owner;
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
public class SocialUser extends Owner implements Serializable{

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty("社交用户id(第三方gitee登录)")
//    @TableId(value = "uid")
    private Integer id;

    //    @ApiModelProperty("登录用户名")
    private String name;

    //    @ApiModelProperty("密码")
//    private String password;

    private String access_token;

    //    @ApiModelProperty("token过期时间")
    private Integer expires_in;

    private String refresh_token;

    private String avatar_url;

//    @TableField(exist = false)
    private String scope;
//
//    @TableField(exist = false)
    private Long created_at;


}
