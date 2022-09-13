package cat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("pm-service-owner")
public interface OwnerClient {

    @PostMapping("/owner/info/{ownerId}")
    String findNameById(@PathVariable Integer ownerId);
}
