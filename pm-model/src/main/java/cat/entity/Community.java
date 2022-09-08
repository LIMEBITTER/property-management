package cat.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auth: zhuan
 * @Desc: 小区实体类（具体属性含义，可以参考小区表tb_community）
 */
@TableName("tb_community")
@Data
public class Community implements Serializable {

    @TableId
    private Integer id;

    private String code;
    private String name;
    private String address;
    private Double area;
    private Integer totalBuildings;
    private Integer totalHouseholds;
    private Integer greeningRate;
    private String thumbnail;
    private String developer;
    private String estateCompany;

//    private Date createTime;
//    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String status;

}
