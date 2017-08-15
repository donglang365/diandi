package com.june.dao;

import org.springframework.stereotype.Repository;

import com.june.bean.User;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDao extends Mapper<User>{
/*    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
*/


	User findUserByName(String name);
}