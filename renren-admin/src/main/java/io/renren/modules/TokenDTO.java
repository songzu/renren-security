package io.renren.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by EalenXie on 2018/11/26 18:49.
 * DTO 返回值token对象
 */
@Data
@AllArgsConstructor
public class TokenDTO {

    private String token;

    private Boolean ifBande = Boolean.TRUE;
}
