package com.rainbow.machinelearning.dao;

import com.rainbow.machinelearning.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @author ${xiami}
 * @version $Id: UserDao.java, v 0.1 2019年05月09日 14:52 Exp $
 */
@Mapper
//@Repository("userDao")
public interface UserDao {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into temp_user (name,age) values (#{name},#{age})")
    void insert(User user);
}