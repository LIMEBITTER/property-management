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
//@ApiModel(value = "Complaint对象", description = "投诉表")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("投诉ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //业务流水号
    private String businessRid;

//    @ApiModelProperty("所属小区名称")
    private String communityName;

//    @ApiModelProperty("所属小区ID")
    private Integer communityId;

//    @ApiModelProperty("投诉人员（业主）ID")
    private Integer ownerId;

//    @ApiModelProperty("投诉人员（业主）名称")
    private String ownerName;

//    @ApiModelProperty("投诉名称")
    private String descriptionName;

//    @ApiModelProperty("投诉事由")
    private String reason;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    @ApiModelProperty("状态：0-未受理，1-已受理（默认），2-已处理完毕")
    private String status;

    private String answer;


}
