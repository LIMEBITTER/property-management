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
 * 物业管理人员表
 * </p>
 *
 * @author zxb
 * @since 2022-09-06
 */
@Data
@TableName("tb_estate_manager")
public class EstateManager implements Serializable {

    private static final long serialVersionUID = 1L;

//   物业管理人员ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//   登录名:登录时使用的名称
    private String userName;

//    密码
    private String password;

    //创建时间 默认插入
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //     更新时间 默认插入
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //管理员的token
    private String token;




}
