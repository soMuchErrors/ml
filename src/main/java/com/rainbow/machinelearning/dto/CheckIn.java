package com.rainbow.machinelearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ${xiami}
 * @version $Id: CheckIn.java, v 0.1 2019年09月17日 11:24 Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckIn implements Serializable{
    private String user;

    private String time;

    private Double latitude;

    private Double longitude;

    private String location;
}
