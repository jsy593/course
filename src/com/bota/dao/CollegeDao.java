package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.College;

public interface CollegeDao {
    int deleteByPrimaryKey(Long id);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);

	/**
	 * 查询所有的学院
	 * @return
	 */
	List<Map<String, Object>> selectAllCollege();
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	List<Map<String, Object>> selectAllCollege(int pageNum,int pageSize);
	
	/**
	 * 批量删除学院
	 * @param ids
	 * @return
	 */
	boolean deleteByPrimaryKeys(String ids);

	/**
	 * 查询学院的数量
	 * @return 
	 */
	Map<String, Object> selectCollegeNumber();
}