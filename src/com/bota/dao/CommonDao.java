package com.bota.dao;

import java.util.List;
import java.util.Map;

import com.bota.util.Params;

/**
 * 类名称：通用DAO接口
 * 描述：处理通用的数据交互
 * 创建人：bota
 * 创建时间：2015-08-05
 */
public interface CommonDao {
	/**添加*/
	public int add(String sql);
	
	/**修改*/
	public int update(String sql);
	
	/**删除*/
	public int delete(String sql);
	
	/**查询单条数据*/
	public Map<String, Object> getMapClass(String sql);
	
	/**查询多条数据*/
	public List<Map<String, Object>> getMapListClass(String sql);
	
	/**查询数量*/
	public long getCount(String sql);
	
	public List<Long> getListObject(String sql);
	
	/**获取单个值*/
	public String getOneValue(String sql);
	
	
	/**查询多条数据*/
	public List<Map<String, Object>> getMapListClass1(Params params);
	
	/**添加*/
	public int add1(Params params);
	
	/**修改*/
	public int update1(Params params);
	
	/**删除*/
	public int delete1(Params params);
	
	/**查询单条数据*/
	public Map<String, Object> getMapClass1(Params params);
	
	/**查询数量*/
	public long getCount1(Params params);
	
	/**获取单个值*/
	public List<Long> getListObject1(Params params);
	
	/**获取单个值*/
	public String getOneValue1(Params params);
	
	/**获取所有数据不分页*/
	public List<Map<String, Object>> getListMap1(Params params);
}
