package cat.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
//@ApiModel(value = "Owner对象", description = "业主表")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("业主ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("所属小区")
    private Integer communityId;

//    @ApiModelProperty("所属房产")
    private Integer houseId;

//    @ApiModelProperty("成员名称")
    private String name;

//    @ApiModelProperty("成员照片")
    private String picture;

//    @ApiModelProperty("身份证号")
    private String idCard;

//    @ApiModelProperty("联系方式")
    private Integer telephone;

//    @ApiModelProperty("职业")
    private String profession;

//    @ApiModelProperty("性别:0-男（默认），1-女")
    private String sex;

//    @ApiModelProperty("类型:0-房主（默认），1-租客")
    private String type;

//    @ApiModelProperty("备注（默认无）")
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    @ApiModelProperty("出生日期")
    private Date birthday;

    private String status;

}
