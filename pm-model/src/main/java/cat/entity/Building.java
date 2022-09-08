package cat.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_building")
public class Building implements Serializable {

    @TableId
    private Integer id;
    private String communityName;
    private Integer  communityId;
    private String name;
    private Integer totalHouseholds;
    private String description;
    private Date createTime;
    private Date updateTime;

}
