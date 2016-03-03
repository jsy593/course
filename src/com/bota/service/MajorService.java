package com.bota.service;

import java.util.List;
import java.util.Map;

import com.bota.bean.Major;

public interface MajorService {
	/**
	 * 查询所有的学院
	 * @return
	 */
	public List<Map<String, Object>> selectAllMajor();
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	public List<Map<String, Object>> selectAllMajor(int pageNum,int pageSize);
	
	/**
	 * 查询学院的数量
	 * @return 
	 */
	Map<String, Object> selectMajorNumber();
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	public Major selectOne(long id);
	/**
	 * 修改学院的信息
	 * @param Major
	 * @return
	 */
	public boolean updateById(Major major);
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	public boolean deleteById(long id);

	/**
	 * 根据id批量删除学院
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String id);

	/**
	 * 添加学院
	 * @param Major
	 * @return
	 */
	public boolean addMajor(Major major);

}
