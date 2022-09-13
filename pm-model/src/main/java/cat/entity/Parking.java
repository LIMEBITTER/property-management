package cat.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;


/**
 * <p>
 * 车位表
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Data
@TableName("tb_parking")
//@ApiModel(value = "Parking对象", description = "车位表")
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("车位ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("所属小区名称")
    private String communityName;
    //
    private Integer ownerId;

//    @ApiModelProperty("所属小区ID")
    private Integer communityId;

//    @ApiModelProperty("车位图片")
    private String picture;

//    @ApiModelProperty("车位编号")
    private String code;

//    @ApiModelProperty("车位名称")
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
