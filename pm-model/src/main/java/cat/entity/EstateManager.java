package cat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 物业管理人员表
 * </p>
 *
 * @author zxb
 * @since 2022-09-06
 */
@Data
@TableName("tb_estate_manager")
//@ApiModel(value = "EstateManager对象", description = "物业管理人员表")
public class EstateManager implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("物业管理人员ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("所属小区名称")
    private String communityName;

//    @ApiModelProperty("所属小区ID")
    private Integer communityId;

//    @ApiModelProperty("登录名:登录时使用的名称")
    private String userName;

//    @ApiModelProperty("真实名称")
    private String name;

//    @ApiModelProperty("密码")
    private String password;

//    @ApiModelProperty("手机")
    private Integer telephone;

//    @ApiModelProperty("邮箱")
    private String email;

//    @ApiModelProperty("角色ID：0-普通用户（默认0），1-管理员用户")
//    private Integer roleId;

//    @ApiModelProperty("创建时间")
    private Date createTime;

//    @ApiModelProperty("更新时间")
    private Date updateTime;

    private String token;



}
