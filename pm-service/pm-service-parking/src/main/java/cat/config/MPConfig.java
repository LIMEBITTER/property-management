package cat.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MPConfig {
    @Bean
    public MybatisPlusInterceptor interceptor(){
        System.out.println("============运行mybatisplus拦截器================");
        //这是mp的拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //将分页拦截器PaginationInnerInterceptor添加到上面的拦截器中
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
