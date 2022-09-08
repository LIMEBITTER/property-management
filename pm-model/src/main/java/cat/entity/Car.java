package cat.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * <p>
 * 车辆表
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Data
@TableName("tb_car")
//@ApiModel(value = "Car对象", description = "车辆表")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("车辆ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("车辆照片")
    private String picture;

//    @ApiModelProperty("所属成员（业主）")
    private Integer ownerId;

//    @ApiModelProperty("车辆颜色")
    private String color;

//    @ApiModelProperty("车牌号")
    private String carNumber;

//    @ApiModelProperty("备注")
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
