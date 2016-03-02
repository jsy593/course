package com.bota.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.College;
import com.bota.dao.CollegeDao;

@Repository("collegeDaoImpl")
public class CollegeDaoImpl extends CommonDaoImpl<College> implements CollegeDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(College record) {
		return 0;
	}

	@Override
	public int insertSelective(College record) {
		return 0;
	}

	@Override
	public College selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(College record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(College record) {
		return 0;
	}
	
	@Override
	public List<Map<String, Object>> selectAllCollege(){
		String sql = "select name,date_format(now(),'%Y-%m-%d') from college";
		return super.findManyBySql(sql);
	}

	@Override
	public List<Map<String, Object>> selectAllCollege(int pageNum, int pageSize){
		int start = (pageNum -1) * pageSize + 1;
		String sql = "select name,date_format(now(),'%Y-%m-%d') from college limit" +start + ","+ pageSize;
		return super.findManyBySql(sql);
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(College.class,"id", ids);
	}
}
