package com.rainbow.machinelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ${xiami}
 * @version $Id: CheckInObject.java, v 0.1 2019年09月17日 12:27 Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckInObject implements Serializable {
    private Long i;

    private Set<String> time;

    private Set<String> location;
}
