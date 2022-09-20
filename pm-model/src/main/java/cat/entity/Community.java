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
 * @Desc: 小区实体类（具体属性含义，可以参考小区表tb_community）
 */
@TableName("tb_community")
@Data
public class Community implements Serializable {

    @TableId
    private Integer id;
    //小区名字
    private String name;
    //小区地址
    private String address;
    //小区面积
    private Double area;
    //小区绿化率
    private Integer greeningRate;
    //物业公司
    private String estateCompany;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
