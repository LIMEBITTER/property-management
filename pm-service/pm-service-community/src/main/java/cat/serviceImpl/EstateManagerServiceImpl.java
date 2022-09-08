package cat.serviceImpl;

import cat.entity.EstateManager;
import cat.mapper.EstateManagerMapper;
import cat.service.EstateManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物业管理人员表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2022-09-06
 */
@Service
public class EstateManagerServiceImpl extends ServiceImpl<EstateManagerMapper, EstateManager> implements EstateManagerService {

}
