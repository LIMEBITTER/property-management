package cat.dto;


import cat.entity.OwnerRole;
import lombok.Data;

@Data
public class Face extends OwnerRole {
    private String file;
    private String groupId;
}
