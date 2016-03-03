package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.Major;

public interface MajorDao {
    int deleteByPrimaryKey(Long id);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

	/**
	 * 查询所有的专业
	 * @return
	 */
	List<Map<String, Object>> selectAllMajor();
	
	/**
	 * 分页查询所有的专业
	 * @return
	 */
	List<Map<String, Object>> selectAllMajor(int pageNum,int pageSize);
	
	/**
	 * 批量删除专业
	 * @param ids
	 * @return
	 */
	boolean deleteByPrimaryKeys(String ids);

	/**
	 * 查询专业的数量
	 * @return 
	 */
	Map<String, Object> selectMajorNumber();
}