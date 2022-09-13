package cat.dto;

import cat.entity.Parking;
import lombok.Data;

@Data
public class ParkingDto extends Parking {
    private String ownerName;
    private String communityName;
}
