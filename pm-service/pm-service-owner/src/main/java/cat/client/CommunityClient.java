package cat.client;

import cat.entity.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import result.R;

@FeignClient("pm-service-community")
public interface CommunityClient {

    @PostMapping("/community/info/{communityId}")
    String findNameById(@PathVariable Integer communityId);
}
