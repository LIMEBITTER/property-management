package cat.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <p>
 * 活动表
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Data
@TableName("tb_activity")
//@ApiModel(value = "Activity对象", description = "活动表")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty("活动ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    @ApiModelProperty("所属小区名称")
    private String communityName;

//    @ApiModelProperty("所属小区ID")
    private Integer communityId;

//    @ApiModelProperty("活动标题")
    private String title;

//    @ApiModelProperty("活动地点")
    private String address;

//    @ApiModelProperty("举办单位")
    private String organizer;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    @ApiModelProperty("活动开始时间")
    private Date startTime;

//    @ApiModelProperty("活动截止时间")
    private Date endTime;

//    @ApiModelProperty("状态:0-活动未开始（默认），1-活动进行中，2-活动已结束")
    private String status;


}
