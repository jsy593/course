package com.bota.dao;

import com.bota.bean.College;

public interface CollegeDao {
    int deleteByPrimaryKey(Long id);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}