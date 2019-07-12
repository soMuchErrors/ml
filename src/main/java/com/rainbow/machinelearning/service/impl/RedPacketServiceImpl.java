package com.rainbow.machinelearning.service.impl;

import com.rainbow.machinelearning.dao.RedPacketDao;
import com.rainbow.machinelearning.model.RedPacket;
import com.rainbow.machinelearning.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ${xiami}
 * @version $Id: RedPacketServiceImpl.java, v 0.1 2019年05月09日 16:22 Exp $
 */

@Service
public class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private RedPacketDao redPacketDao;

    @Transactional
    @Override
    public void add(RedPacket redPacket) {
        redPacketDao.add(redPacket);
    }
}