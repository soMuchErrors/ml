package com.rainbow.machinelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${xiami}
 * @version $Id: AppDto.java, v 0.1 2019年09月19日 10:25 Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDto {
    private String packageName;

    private String appName;

    private String cls;

    private String keyWords;

    private String introduction;
}
