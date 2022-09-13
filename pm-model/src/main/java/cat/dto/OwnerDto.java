package cat.dto;

import cat.entity.Owner;
import lombok.Data;

import java.util.List;

@Data
public class OwnerDto extends Owner {
    private String communityName;
    private List<OwnerDto> communityNameList;
}
