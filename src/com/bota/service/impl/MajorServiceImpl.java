package com.bota.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bota.bean.Major;
import com.bota.dao.MajorDao;
import com.bota.service.MajorService;

@Service("majorServiceImpl")
public class MajorServiceImpl implements MajorService{
	
	@Resource
	private MajorDao majorDao;
	
	@Resource
	private MajorDao majorDaoImpl;
	
	/**
	 * 查询所有的学院
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllMajor(){
		return majorDaoImpl.selectAllMajor();
	}
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllMajor(int pageNum,int pageSize){
		return majorDaoImpl.selectAllMajor(pageNum,pageSize);
	}
	
	/**
	 * 查询学院的数量
	 * @return 
	 */
	public Map<String, Object> selectMajorNumber(){
		return majorDaoImpl.selectMajorNumber();
	}
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	@Override
	public Major selectOne(long id){
		return majorDao.selectByPrimaryKey(id);
	}
	/**
	 * 修改学院的信息
	 * @param Major
	 * @return
	 */
	@Override
	public boolean updateById(Major Major){
		return majorDao.updateByPrimaryKeySelective(Major) > 0;
	}
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteById(long id){
		return majorDao.deleteByPrimaryKey(id) > 0;
	}
	
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids){
		return majorDao.deleteByPrimaryKeys(ids);
	}

	/**
	 * 添加学院
	 */
	@Override
	public boolean addMajor(Major major) {
		return majorDao.insertSelective(major) > 0;
	}
	
}
