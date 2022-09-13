package cat.service;

import cat.entity.Community;
import cat.entity.QueryPageBean;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import result.R;

/**
 * <p>
 * 小区表 服务类
 * </p>
 *
 * @author zxb
 * @since 2022-09-05
 */
public interface CommunityService extends IService<Community> {

}
