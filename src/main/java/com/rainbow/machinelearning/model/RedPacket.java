package com.rainbow.machinelearning.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ${xiami}
 * @version $Id: RedPacket.java, v 0.1 2019年05月09日 16:16 Exp $
 */
@Data
public class RedPacket implements Serializable {
    private long redPacketId;
    private long userId;
    private Double redPacketAmount;
}