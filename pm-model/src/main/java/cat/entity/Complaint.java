package cat.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 投诉表
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Data
@TableName("tb_complaint")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

//    投诉ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    所属小区名称
    private String communityName;

//    所属小区ID
    private Integer communityId;

//    投诉人员（业主）ID
    private Integer ownerId;

//    投诉人员（业主）名称
    private String ownerName;

//    投诉名称
    private String descriptionName;

//    投诉事由
    private String reason;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//   状态：0-未受理，1-已受理（默认），2-已处理完毕
    private String status;

    //投诉的回复
    private String answer;
    //投诉分类id
    private Integer typeId;


}
