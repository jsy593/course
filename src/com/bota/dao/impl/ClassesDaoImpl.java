package com.bota.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bota.bean.Classes;
import com.bota.dao.ClassesDao;

@Repository("classesDaoImpl")
public class ClassesDaoImpl extends CommonDaoImpl<Classes> implements ClassesDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Classes record) {
		return 0;
	}

	@Override
	public int insertSelective(Classes record) {
		return 0;
	}

	@Override
	public Classes selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Classes record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Classes record) {
		return 0;
	}
	
	@Override
	public List<Map<String, Object>> selectAllClass(){
		String sql = "select id,name,date_format(createTime,'%Y-%m-%d') createTime from classes";
		return super.findManyBySql(sql);
	}

	@Override
	public List<Map<String, Object>> selectAllClass(int pageNum, int pageSize){
		int start = (pageNum -1) * pageSize;
		String sql = "select c.id,c.name,date_format(c.createTime,'%Y-%m-%d') createTime,m.name mname "
				+ "from classes c,major m where c.majorId=m.id order by c.createTime desc limit " +start + ","+ pageSize;
//		System.out.println(sql);
		List<Map<String, Object>> listMap = super.findManyBySql(sql);
		return listMap;
	}
	
	@Override
	public Map<String, Object> selectClassNumber(){
		String sql = "select count(*) count from classes";
		Map<String, Object> listMap = super.findBySql(sql);
		return listMap;
	}
	
	@Override
	public boolean deleteByPrimaryKeys(String ids){
		return super.deletetClass(Classes.class,"id", ids);
	}
}
