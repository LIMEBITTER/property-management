package cat.serviceImpl;

import cat.entity.Parking;
import cat.mapper.ParkingMapper;
import cat.service.ParkingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车位表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Service
public class ParkingServiceImpl extends ServiceImpl<ParkingMapper, Parking> implements ParkingService {

}
