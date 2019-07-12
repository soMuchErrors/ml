package com.rainbow.machinelearning.dao;

import com.rainbow.machinelearning.model.RedPacket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author ${xiami}
 * @version $Id: RedPacketDao.java, v 0.1 2019年05月09日 16:17 Exp $
 */
@Mapper
public interface RedPacketDao {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into red_packet (user_id,red_packet_amount) values (#{userId},#{redPacketAmount})")
    void add(RedPacket redPacket);
}