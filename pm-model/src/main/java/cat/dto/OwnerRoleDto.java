package cat.dto;

import cat.entity.OwnerRole;
import lombok.Data;

import java.io.Serializable;

@Data
public class OwnerRoleDto extends OwnerRole implements Serializable {
    private Integer id;
    private String name;
    private String avatar_url;


}
