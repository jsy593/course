package com.bota.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.Major;
import com.bota.dao.MajorDao;

@Repository("majorDaoImpl")
public class MajorDaoImpl extends CommonDaoImpl<Major> implements MajorDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Major record) {
		return 0;
	}

	@Override
	public int insertSelective(Major record) {
		return 0;
	}

	@Override
	public Major selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Major record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Major record) {
		return 0;
	}
	
	@Override
	public List<Map<String, Object>> selectAllMajor(){
		String sql = "select id,name,date_format(createTime,'%Y-%m-%d') createTime from major";
		return super.findManyBySql(sql);
	}

	@Override
	public List<Map<String, Object>> selectAllMajor(int pageNum, int pageSize){
		int start = (pageNum -1) * pageSize;
		String sql = "select m.id,m.name,date_format(m.createTime,'%Y-%m-%d') createTime,c.name cname from major m,college c where m.collegeId=c.id limit " +start + ","+ pageSize;
//		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql);
		return listMap;
	}
	
	@Override
	public Map<String, Object> selectMajorNumber(){
		String sql = "select count(*) count from major";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(Major.class,"id", ids);
	}
}
