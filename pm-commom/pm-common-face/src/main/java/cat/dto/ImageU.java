package cat.dto;

import cat.constant.ImageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图像对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageU implements Serializable {

    private ImageTypeEnum imageTypeEnum;

    private String data;
}

