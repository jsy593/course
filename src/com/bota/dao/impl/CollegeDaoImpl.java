package com.bota.dao.impl;

import java.util.Date;
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
		College college = new College();
		college.setCreatetime(new Date());
		college.setId(2l);
		return college;
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
		String sql = "select id,name,createTime from college";
		return super.findManyBySql(sql);
	}

	@Override
	public List<Map<String, Object>> selectAllCollege(int pageNum, int pageSize){
		int start = (pageNum -1) * pageSize;
		String sql = "select id,name,date_format(createTime,'%Y-%m-%d') createTime from college order by createTime desc limit " +start + ","+ pageSize;
		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql);
		return listMap;
	}
	
	@Override
	public Map<String, Object> selectCollegeNumber(){
		String sql = "select count(*) count from college";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(College.class,"id", ids);
	}
}
