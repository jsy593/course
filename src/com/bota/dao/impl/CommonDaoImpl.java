package com.bota.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bota.dao.CommonDao;
import com.bota.util.Params;
/**
 * 类名称：通用DAO实现层
 * 描述：通用数据处理层
 * 创建人：bota
 * 创建时间：2015-08-05
 */
@Repository("commonDaoImpl")
public class CommonDaoImpl<T> {
	@Resource
	private CommonDao commonDao;

	/**
	 * 根据Sql语句进行查询单条
	 * 
	 * @author bota
	 * @param sql sql语句
	 * @return
	 */
	public Map<String,Object> findBySql(String sql) {
		return commonDao.getMapClass(sql);
	}
	
	public String getOneValue(String sql) {
		return commonDao.getOneValue(sql);
	}
	
	/**
	 * 验证查看是否存在
	 * 
	 * @author bota
	 * @param sql
	 * @return
	 */
	public boolean validate(String sql) {
		return commonDao.getCount(sql) > 0;
	}
	
	/**
	 * 根据Sql语句进行查询单条
	 * 
	 * @author bota
	 * @param sql sql语句
	 * @return
	 */
	public List<Map<String,Object>> findManyBySql(String sql) {
		return commonDao.getMapListClass(sql);
	}
	
	/**
	 * 通过自己书写语句进行添加
	 * 
	 * @author bota
	 * @param sql 添加语句
	 * @return
	 */
	public boolean addClass(String sql) {
		return commonDao.add(sql) > 0;
	}
	
	/**
	 * 通过自己书写语句进行修改
	 * 
	 * @author bota
	 * @param sql 修改语句
	 * @return
	 */
	public boolean updateClass(String sql) {
		return commonDao.add(sql) > 0;
	}
	
	/**
	 * 添加实体
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param addData 添加的数据
	 * @return 成功或失败
	 */
	public boolean addClass(Class<T> entityName, Map<String, Object> addData) {
		boolean bool = false;
		try {
			//创建StringBuffer接受添加SQL语句
			StringBuffer sb = new StringBuffer("insert into ");
			sb.append(getTableName(entityName)).append('(');
			
			//获取所有key（字段名）
			Iterator<String> it = addData.keySet().iterator();
			
			//创建StringBuffer存储所有字段key（字段名）
			StringBuffer column = new StringBuffer();
			
			//创建StringBuffer存储所有字段value（字段名对应的值）
			StringBuffer values = new StringBuffer();
			
			/**循环拼接SQL语句*/
			while (it.hasNext()) {
				String key = it.next();
				column.append(key).append(',');
				values.append("'").append(addData.get(key)).append("',");
			}
			
			/**获取拼接的长度去掉最后一个“,”*/
			int coulmnLength = column.length();
			int valueLength = values.length();
			column.replace(coulmnLength-1, coulmnLength, ")");
			values.replace(valueLength-1, valueLength, ")");
			
			/**组装SQL并执行*/
			sb.append(column).append(" values(").append(values);
			bool = commonDao.add(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 通过主键ID删除单条数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param primaryKeyName 主键字段名
	 * @param entityId 主键ID值
	 * @return 成功或失败
	 */
	public boolean deleteClass(Class<T> entityName, String primaryKeyName, Long entityId) {
		boolean bool = false;
		try {
			//创建StringBuffer接受删除SQL语句
			StringBuffer sb = new StringBuffer("delete from ");
			
			/**组装SQL并执行*/
			sb.append(getTableName(entityName))
			.append(" where ").append(primaryKeyName).append('=').append(entityId);
			bool = commonDao.delete(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 通过主键ID删除多条数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param primaryKeyName 主键字段名
	 * @param entityIds 主键ID值集合
	 * @return 成功或失败
	 */
	public boolean deletetClass(Class<T> entityName, String primaryKeyName, String entityIds) {
		boolean bool = false;
		try {
			//创建StringBuffer接受删除SQL语句
			StringBuffer sb = new StringBuffer("delete from ");
			
			/**组装SQL并执行*/
			sb.append(getTableName(entityName))
			.append(" where ").append(primaryKeyName).append(" in(").append(entityIds).append(')');
			bool = commonDao.delete(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 通过条件语句删除多条数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param whereSql 条件语句
	 * @return 成功或失败
	 */
	public boolean deleteClass(Class<T> entityName, String whereSql) {
		boolean bool = false;
		try {
			//创建StringBuffer接受删除SQL语句
			StringBuffer sb = new StringBuffer("delete from ");
			
			/**组装SQL并执行*/
			sb.append(getTableName(entityName)).append(whereSql);
			bool = commonDao.delete(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 修改状态
	 * @author bota
	 * @param entityName 实体Class
	 * @param stateName 状态字段名
	 * @param state 状态值
	 * @param primaryKeyName 主键字段名
	 * @param entityId 实体主键值
	 * @return 成功或失败
	 */
	public boolean update(Class<T> entityName, String stateName, Object state,
			String primaryKeyName, long entityId) {
		boolean bool = false;
		try {
			StringBuffer sb = new StringBuffer("update ");
			sb.append(getTableName(entityName)).append(" set ")
			.append(stateName).append("=\"").append(state.toString()).append("\"")
			.append(" where ").append(primaryKeyName).append('=').append(entityId);
			bool = commonDao.update(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 批量修改状态
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param stateName 状态字段名
	 * @param state 状态值
	 * @param primaryKeyName 主键字段名
	 * @param entityId 实体主键值集合
	 * @return 成功或失败
	 */
	public boolean update(Class<T> entityName, String stateName, int state,
			String primaryKeyName, String entityIds) {
		boolean bool = false;
		try {
			StringBuffer sb = new StringBuffer("update ");
			sb.append(getTableName(entityName)).append(" set ")
			.append(stateName).append('=').append(state)
			.append(" where ").append(primaryKeyName).append(" in(").append(entityIds).append(')');
			bool = commonDao.update(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 通过主键ID修改单条数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param primaryKeyName 主键字段名
	 * @return 成功或失败
	 */
	public boolean update(Class<T> entityName, Map<String, Object> updateData, 
			String primaryKeyName) {
		boolean bool = true;
		try {
			StringBuffer sb = new StringBuffer("update ");
			sb.append(getTableName(entityName)).append(" set ");
			Iterator<String> it = updateData.keySet().iterator();
			StringBuffer updateBuffer = new StringBuffer();
			
			while (it.hasNext()) {
				String key = it.next();
				if(key.equals(primaryKeyName)) {
					continue;
				} 
				if(updateData.get(key) == null || updateData.get(key).equals("")) {
					updateBuffer.append(key).append("=").append("null").append(",");
				} else{
					updateBuffer.append(key).append("='").append(updateData.get(key)).append("',");
				}
			}
			
			sb.append(updateBuffer.substring(0, updateBuffer.length() - 1)).append(" where ")
			.append(primaryKeyName).append('=').append(updateData.get(primaryKeyName));
			System.out.println("sql："+sb.toString());
			bool = commonDao.update(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 通过条件修改实体
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param updataData 修改数据
	 * @param whereSql 条件语句
	 * @return 成功或失败
	 */
	public boolean updateByWhere(Class<T> entityName, Map<String , Object> updateData, String whereSql){
		boolean bool = false;
		try {
			StringBuffer sb = new StringBuffer("update ");
			sb.append(getTableName(entityName)).append(" set ");
			Iterator<String> it = updateData.keySet().iterator();
			StringBuffer updateBuffer = new StringBuffer();
			
			while (it.hasNext()) {
				String key = it.next();
				if(updateData.get(key) == null || updateData.get(key).equals("")) {
					updateBuffer.append(key).append("=").append("null").append(",");
				} else{
					updateBuffer.append(key).append("='").append(updateData.get(key)).append("',");
				}
			}
			sb.append(updateBuffer.substring(0, updateBuffer.length() - 1)).append(whereSql);
			bool = commonDao.update(sb.toString()) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 通过条件获取数量
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param whereSql SQL语句
	 * @return 数量
	 */
	public long getCount(Class<T> entityName, String whereSql) {
		long count = 0;
		try {
			String countSql = "select count(*) from " + getTableName(entityName) + whereSql;
			count = commonDao.getCount(countSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 通过条件获取数量
	 * 
	 * @author hj
	 * @param countSql SQL语句
	 * @return 数量
	 */
	public long getCount(String countSql) {
		long count = 0;
		try {
			count = commonDao.getCount(countSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 拼接的SQL语句
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param whereSql 条件语句
	 * @return 返回组装后的SQL语句
	 */
	public StringBuffer appendSQL(Class<T> entityName, String whereSql) {
		StringBuffer sb = new StringBuffer("select ");
		Field[] attrObjects = entityName.getDeclaredFields(); 
		
		for (Field attrObject : attrObjects) {
			String attrName = attrObject.getName();
			sb.append(attrName).append(',');
		}
		int length = sb.length();
		sb.delete(length-1, length);
		
		sb.append(" from ").append(getTableName(entityName))
		.append(whereSql);
		return sb;
	}
	
	/**
	 * 通过条件查询单个实体数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param whereSql 条件语句
	 * @return 返回单个Map数据集合
	 */
	public Map<String, Object> getMapClass(Class<T> entityName, String whereSql) {
		Map<String, Object> mapData = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb = appendSQL(entityName, whereSql);
			mapData = commonDao.getMapClass(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	/**
	 * 通过方法参数查询分页数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param page 当前显示页数
	 * @param rows 每页显示数据条数
	 * @param whereSql 条件语句
	 * @param orderBy 排序规则
	 * @return 分页的map结果集
	 */
	public Map<String, Object> getMapListClass(Class<T> entityName, int page, int rows,
			String whereSql, Map<String, Object> orderBy) {
		Map<String, Object> mapData = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb = appendSQL(entityName, whereSql);
			sb.append(appendOrder(orderBy));
			if(page > 0) {
				sb.append(" limit ").append((page-1)*rows).append(',').append(rows);
			}
			System.out.println("语句："+sb.toString());
			List<Map<String, Object>> listMap = commonDao.getMapListClass(sb.toString());
			long count = getCount(entityName, whereSql);
			mapData = new HashMap<String, Object>();
			mapData.put("total", count);
			mapData.put("rows", listMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	/**
	 * 通过方法参数查询分页数据
	 * 
	 * @author bota
	 * @param sql 自己写的查询语句
	 * @param page 当前显示页数
	 * @param rows 每页显示数据条数
	 * @param whereSql 条件语句
	 * @param orderBy 排序规则
	 * @return 分页的map结果集
	 */
	public Map<String, Object> getMapListClass(String sql,String countSql, int page, int rows,
			String whereSql, Map<String, Object> orderBy) {
		Map<String, Object> mapData = null;
		try {
			StringBuffer sb = new StringBuffer();
			if(whereSql == null) {
				whereSql = "";
			}
			sb.append(sql+whereSql).append(appendOrder(orderBy));
			
			if(page > 0 ) {
				sb.append(" limit ").append((page-1)*rows).append(',').append(rows);
			}
			System.out.println(sb.toString());
			List<Map<String, Object>> listMap = commonDao.getMapListClass(sb.toString());
			long count=0;
			if(!countSql.equals("")&&countSql!=""){
				count = commonDao.getCount(countSql+whereSql.replaceAll(" GROUP BY us.id", ""));
			}
//			System.out.println("conuntSql:"+countSql+whereSql.replaceAll(" GROUP BY us.id", ""));
			
			mapData = new HashMap<String, Object>();
			mapData.put("total", count);
			mapData.put("rows", listMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	/**
	 * 查询所有数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @return 分页的map结果集
	 */
	public List<Map<String, Object>> getListAll(Class<T> entityName) {
		List<Map<String, Object>> listData = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(appendSQL(entityName, ""));
			listData = commonDao.getMapListClass(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	public List<Map<String, Object>> getListAll(String sql) {
		List<Map<String, Object>> listData = null;
		try {
			listData = commonDao.getMapListClass(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	/**
	 * 查询所有ID与Name
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @return 分页的map结果集
	 */
	public List<Map<String, Object>> getSelectList(Class<T> entityName, String primaryKey, String name) {
		List<Map<String, Object>> listData = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select ").append(primaryKey).append(',').append(name)
			.append(" from ").append(getTableName(entityName));
			listData = commonDao.getMapListClass(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	/**
	 * 查询单个字段
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param searchColumn 查询的字段名
	 * @param whereSql 查询的
	 * @return
	 */
	public List<Long> getListOne(Class<T> entityName, String searchColumn, String whereSql) {
		List<Long> listData = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select ").append(searchColumn.toUpperCase())
			.append(" from ").append(getTableName(entityName))
			.append(whereSql);
			listData = commonDao.getListObject(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	/**
	 * 根据条件查询所有数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param whereSql 查询的条件
	 * @return 分页的map结果集
	 */
	public List<Map<String, Object>> getListByWhere(Class<T> entityName, String whereSql) {
		List<Map<String, Object>> listData = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(appendSQL(entityName, whereSql));
			System.out.println("需要查找的订单："+sb.toString());
			listData = commonDao.getMapListClass(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	/**
	 * 根据主键ID查询单条数据
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @param primaryKey 主键名
	 * @param entityId 主键ID
	 * @return
	 */
	public Map<String, Object> findById(Class<T> entityName, String primaryKey, long entityId) {
		StringBuffer sb = new StringBuffer();
		String whereSql = " where " + primaryKey + " = " + entityId;
		sb.append(appendSQL(entityName, whereSql));
		System.out.println(sb);
		return commonDao.getMapClass(sb.toString());
	}
	
	/**
	 * 组装排序语句
	 * 
	 * @author bota
	 * @param orderBy 排序参数
	 * @return 排序语句
	 */
	private String appendOrder(Map<String, Object> orderBy) {
		String order = "";
		try {
			if(orderBy == null || orderBy.size() < 1) {
				return "";
			}
			String key = orderBy.keySet().iterator().next();
			order = " order by " + key + ' ' + orderBy.get(key);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	/**
	 * 得到实体名
	 * 
	 * @author bota
	 * @param entityName 实体Class
	 * @return 实体名
	 */
	private String getTableName(Class<? extends Object> entityName) {
		return entityName.getSimpleName();
	}
	
	
	/**查询多条数据*/
	public Map<String, Object> getMapListClass1(Params params) {
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
		}
		Map<String, Object> mapData = null;
		Integer start = params.getStart();
		if(start != null && start > -1) {
			params.setStart(start*params.getEnd());
		}
		try {
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			listMap = commonDao.getMapListClass1(params);
			mapData = new HashMap<String, Object>();
			if(listMap != null && listMap.size() > 0) {
				mapData.put("rows", listMap);
				long total = commonDao.getCount1(params);
				mapData.put("total", total);
			} else {
				mapData.put("rows", listMap);
				mapData.put("total", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapData;
	}
	
	/**添加*/
	public int add1(Params params) {
		return commonDao.add1(params);
	}
	
	/**修改*/
	public int update1(Params params) {
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
			if(params.getParamList() == null || params.getParamList().size() < 1
					|| params.getParamList().get(0).getValue() ==null) {
				return 0;
			}
		}
		return commonDao.update1(params);
	}
	
	/**删除*/
	public int delete1(Params params) {
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
			if(params.getParamList() == null || params.getParamList().size() < 1
					|| params.getParamList().get(0).getValue() ==null) {
				return 0;
			}
		}
		return commonDao.delete1(params);
	}
	
	/**查询单条数据*/
	public Map<String, Object> getMapClass1(Params params) {
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
		}
		return commonDao.getMapClass1(params);
	}
	
	/**查询数量*/
	public long getCount1(Params params) {
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
		}
		return commonDao.getCount1(params);
	}
	
	public List<Long> getListObject1(Params params){
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
		}
		return commonDao.getListObject1(params);
	}
	
	public List<Map<String,Object>> getListMap1(Params params){
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
		}
		return commonDao.getListMap1(params);
	}
	
	/**获取单个值*/
	public String getOneValue1(Params params) {
		if(params.getWhereSql() == null || params.getWhereSql().equals("")) {
			params.setWhereSql("1=1"); 
		}
		return commonDao.getOneValue1(params);
	}
	
	
}
