package cat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("pm-service-community")
public interface CommunityClient {

    @PostMapping("/community/info/{communityId}")
    String findNameById(@PathVariable Integer communityId);
}
