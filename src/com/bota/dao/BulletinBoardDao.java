package com.bota.dao;

import com.bota.bean.BulletinBoard;

public interface BulletinBoardDao {
    int deleteByPrimaryKey(Long id);

    int insert(BulletinBoard record);

    int insertSelective(BulletinBoard record);

    BulletinBoard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BulletinBoard record);

    int updateByPrimaryKey(BulletinBoard record);
}