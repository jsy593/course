package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.bean.Classes;

public interface ClassesDao {
    int deleteByPrimaryKey(Long id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

	/**
	 * 查询所有的专业
	 * @return
	 */
	List<Map<String, Object>> selectAllClass();
	
	/**
	 * 分页查询所有的专业
	 * @return
	 */
	List<Map<String, Object>> selectAllClass(int pageNum,int pageSize);
	
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
	Map<String, Object> selectClassNumber();
}