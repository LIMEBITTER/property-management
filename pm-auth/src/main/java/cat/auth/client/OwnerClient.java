package cat.auth.client;

import cat.dto.OwnerRoleDto;
import cat.vo.SocialUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@FeignClient("pm-service-owner")
public interface OwnerClient {
    @PostMapping("owner/ownerRole/gitee/authLogin")
    public String authLogin(OwnerRoleDto ownerRoleDto);
}
