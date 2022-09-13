package cat.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 网关拦截
 *
 * @author ding
 */
@Component
@Slf4j
public class GatewayFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher pathMatcher=new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String requestURI = request.getURI().getPath();
        log.info("接收到请求：{}", requestURI);

        //1、定义不被过滤的路径，资源
        String[] urls={

                "/community/estateManager/login",
        };
        boolean flag = checkUrl(urls, requestURI);
        log.info("路径匹配返回值：{}",flag);
        if (flag){
            return chain.filter(exchange);
        }

        System.out.println("=========="+request.getHeaders());
        if (request.getHeaders().getFirst("token").length()>0){
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
        return exchange.getResponse().setComplete();
    }

    /**
     * 认证
     */
//    private boolean auth(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 逻辑自行实现
//        String token = this.getToken(exchange.getRequest());
//        log.info("token:{}", token);
//        return true;
//    }

    /**
     * 获取token
     */
//    public String getToken(ServerHttpRequest request) {
//        String token = request.getHeaders().getFirst("token");
//        if (StringUtils.isb.isNull(token)) {
//            return request.getQueryParams().getFirst("token");
//        }
//        return token;
//    }


    public boolean checkUrl(String[] urls,String uri){
        for (String url : urls) {
            boolean flag = pathMatcher.match(url, uri);
            if (flag){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}






