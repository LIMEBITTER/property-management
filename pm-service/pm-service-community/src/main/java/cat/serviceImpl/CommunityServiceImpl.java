package cat.serviceImpl;

import cat.entity.Community;
import cat.mapper.CommunityMapper;
import cat.service.CommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小区表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2022-09-05
 */
@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

}
