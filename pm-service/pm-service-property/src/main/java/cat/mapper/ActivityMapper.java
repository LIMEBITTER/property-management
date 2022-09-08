package cat.mapper;

import cat.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

}
