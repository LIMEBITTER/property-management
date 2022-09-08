package cat.serviceImpl;

import cat.entity.Complaint;
import cat.mapper.ComplaintMapper;
import cat.service.ComplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 投诉表 服务实现类
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {

}
