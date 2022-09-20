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

//    车位ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //小区业主id
    private Integer ownerId;

//   车位编号
    private String code;

//   车位名称
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //车位使用状态
    private Integer status;

    //若被使用时，该车位的车牌号
    private String carNumber;

    //车辆颜色
    private String color;

    //备注
    private String remark;


}
