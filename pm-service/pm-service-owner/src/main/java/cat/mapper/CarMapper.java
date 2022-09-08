package cat.mapper;

import cat.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车辆表 Mapper 接口
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
