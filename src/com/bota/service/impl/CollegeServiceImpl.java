package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.College;
import com.bota.dao.CollegeDao;
import com.bota.service.CollegeService;

@Service("CollegeServiceImpl")
public class CollegeServiceImpl implements CollegeService{
	
	@Resource
	private CollegeDao collegeDao;
	
	@Resource
	private CollegeDao collegeDaoImpl;
	
	/**
	 * 查询所有的学院
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllCollege(){
		return collegeDaoImpl.selectAllCollege();
	}
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllCollege(int pageNum,int pageSize){
		return collegeDaoImpl.selectAllCollege(pageNum,pageSize);
	}
	
	/**
	 * 查询学院的数量
	 * @return 
	 */
	public Map<String, Object> selectCollegeNumber(){
		return collegeDaoImpl.selectCollegeNumber();
	}
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	@Override
	public College selectOne(long id){
		return collegeDao.selectByPrimaryKey(id);
	}
	/**
	 * 修改学院的信息
	 * @param college
	 * @return
	 */
	@Override
	public boolean updateById(College college){
		return collegeDao.updateByPrimaryKeySelective(college) > 0;
	}
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(long id){
		return collegeDao.deleteByPrimaryKey(id) > 0;
	}
	
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids){
		return collegeDao.deleteByPrimaryKeys(ids);
	}
	
}
