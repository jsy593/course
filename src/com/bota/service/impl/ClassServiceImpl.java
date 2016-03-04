package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.Classes;
import com.bota.dao.ClassesDao;
import com.bota.service.ClassService;

@Service("classServiceImpl")
public class ClassServiceImpl implements ClassService{
	
	@Resource
	private ClassesDao classesDao;
	
	@Resource
	private ClassesDao classesDaoImpl;
	
	/**
	 * 查询所有的学院
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllClasses(){
		return classesDaoImpl.selectAllClass();
	}
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllClasses(int pageNum,int pageSize){
		return classesDaoImpl.selectAllClass(pageNum,pageSize);
	}
	
	/**
	 * 查询学院的数量
	 * @return 
	 */
	public Map<String, Object> selectClassesNumber(){
		return classesDaoImpl.selectClassNumber();
	}
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	@Override
	public Classes selectOne(long id){
		return classesDao.selectByPrimaryKey(id);
	}
	/**
	 * 修改学院的信息
	 * @param Class
	 * @return
	 */
	@Override
	public boolean updateById(Classes classes){
		return classesDao.updateByPrimaryKeySelective(classes) > 0;
	}
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(long id){
		return classesDao.deleteByPrimaryKey(id) > 0;
	}
	
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids){
		return classesDao.deleteByPrimaryKeys(ids);
	}

	/**
	 * 添加学院
	 */
	@Override
	public boolean addClass(Classes classes) {
		return classesDao.insertSelective(classes) > 0;
	}
	
}
