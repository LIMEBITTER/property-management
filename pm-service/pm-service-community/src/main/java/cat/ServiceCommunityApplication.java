package cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@EnableRedisHttpSession
public class ServiceCommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCommunityApplication.class, args);
    }
}
