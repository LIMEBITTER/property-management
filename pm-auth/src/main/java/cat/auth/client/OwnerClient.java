package cat.auth.client;

import cat.dto.OwnerRoleDto;
import cat.entity.Owner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("pm-service-owner")
public interface OwnerClient {
    @PostMapping("owner/info/gitee/authLogin")
    public String authLogin(Owner owner);
}
