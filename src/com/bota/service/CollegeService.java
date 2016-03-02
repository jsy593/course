package com.bota.service;

import java.util.List;
import java.util.Map;

import com.bota.bean.College;

public interface CollegeService {
	/**
	 * 查询所有的学院
	 * @return
	 */
	public List<Map<String, Object>> selectAllCollege();
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	public List<Map<String, Object>> selectAllCollege(int pageNum,int pageSize);
	
	/**
	 * 查询学院的数量
	 * @return 
	 */
	Map<String, Object> selectCollegeNumber();
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	public College selectOne(long id);
	/**
	 * 修改学院的信息
	 * @param college
	 * @return
	 */
	public boolean updateById(College college);
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

}
