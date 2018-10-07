package ru.tamagotchi.basicmechanics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by makar
 * 07.10.2018 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Version")
public class VersionDto {
    @ApiModelProperty(
            value = "Версия в формате &lt;version &gt;.&lt;git branch &gt;.&lt;commit hash&gt;.&lt;commints count&gt;"
    )
    private String version;
}
