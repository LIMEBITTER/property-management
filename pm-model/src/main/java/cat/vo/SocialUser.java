package cat.vo;

import lombok.Data;

@Data
public class SocialUser {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String scope;
    private Long created_at;

}
