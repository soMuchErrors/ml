package com.rainbow.machinelearning.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ${xiami}
 * @version $Id: User.java, v 0.1 2019年05月09日 14:52 Exp $
 */
@Data
public class User implements Serializable {
    private long id;
    private String name;
    private int age;
}