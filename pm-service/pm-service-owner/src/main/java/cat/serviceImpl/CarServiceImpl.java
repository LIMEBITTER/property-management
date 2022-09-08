package cat.serviceImpl;

import cat.entity.Car;
import cat.mapper.CarMapper;
import cat.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
