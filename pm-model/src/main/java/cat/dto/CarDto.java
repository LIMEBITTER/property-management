package cat.dto;

import cat.entity.Car;
import lombok.Data;

@Data
public class CarDto extends Car {
    private String ownerName;

}
