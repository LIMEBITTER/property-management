package cat.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共字段的自动填充功能
 */
@Slf4j
@Component
public class PropertyMetaData implements MetaObjectHandler {

    //插入数据操作的时候，进行字段的自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
      log.info("插入自动填充:{}", metaObject.toString());
      metaObject.setValue("createTime", LocalDateTime.now());
      metaObject.setValue("updateTime", LocalDateTime.now());
//      metaObject.setValue("createUser", 1L);
//      metaObject.setValue("updateUser",1L);
    }
    //做修改的时候，做字段的填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("修改自动填充:{}", metaObject.toString());
        metaObject.setValue("updateTime", LocalDateTime.now());
//        metaObject.setValue("updateUser",1L);

    }
}
