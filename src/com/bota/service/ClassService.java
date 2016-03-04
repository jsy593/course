package com.bota.service;

import java.util.List;
import java.util.Map;

import com.bota.bean.Classes;

public interface ClassService {
	/**
	 * 查询所有的班级
	 * @return
	 */
	public List<Map<String, Object>> selectAllClasses();
	
	/**
	 * 分页查询所有的班级
	 * @return
	 */
	public List<Map<String, Object>> selectAllClasses(int pageNum,int pageSize);
	
	/**
	 * 查询班级的数量
	 * @return 
	 */
	Map<String, Object> selectClassesNumber();
	
	/**
	 * 根据id查询班级
	 * @param id
	 * @return
	 */
	public Classes selectOne(long id);
	/**
	 * 修改班级的信息
	 * @param classes
	 * @return
	 */
	public boolean updateById(Classes classes);
	/**
	 * 根据id删除班级
	 * @param id
	 * @return
	 */
	public boolean deleteById(long id);

	/**
	 * 根据id批量删除班级
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String id);

	/**
	 * 添加班级
	 * @param classes
	 * @return
	 */
	public boolean addClass(Classes classes);
}
