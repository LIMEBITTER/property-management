package cat.serviceImpl;

import cat.entity.Owner;
import cat.mapper.OwnerMapper;
import cat.service.OwnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业主表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {

}
