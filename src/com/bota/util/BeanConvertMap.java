package com.bota.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class BeanConvertMap {
	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @param  
	 * @return 转化出来的 Map 对象
	 */
	
	public static Map<String, Object> convert(Object bean){
		Class clazz = bean.getClass();
		Map dataMap = new HashMap();
		BeanInfo beanInfo ;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor descriptor : descriptors){
				String propertyName = descriptor.getName();
				if(!propertyName.equals("class")){
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if(result != null){
						dataMap.put(propertyName, result);
					}else{
						dataMap.put(propertyName, "");
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> convertBean(Object bean) {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		
		return returnMap;
	}
	
	
	

	/**
	 * 将一个Map对象转化为一个JavaBean
	 * 
	 * @param map
	 *            包含属性值的map
	 * @param bean
	 *            要转化的类型
	 * @return beanObj 转化出来的JavaBean对象
	 */
	public static <T> T convertMap(Class<T> clazz, Map<String, Object> paramMap) {
		T beanObj = null;
		try {
			beanObj = clazz.newInstance();
			String propertyName = null;
			Object propertyValue = null;
			for (Map.Entry<String, Object> entity : paramMap.entrySet()) {
				propertyName = entity.getKey();
				if(propertyName.equals("userId")){
					System.out.println(propertyName);
				}
				propertyValue = entity.getValue();
				setProperties(beanObj, propertyName, propertyValue);
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("不合法或不正确的参数", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("实例化JavaBean失败", e);
		} catch (Exception e) {
			throw new RuntimeException("Map转换为Java Bean对象失败", e);
		}

		return beanObj;
	}

	private static <T> void setProperties(T entity, String propertyName,
			Object value) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName,
				entity.getClass());
		Method methodSet = pd.getWriteMethod();
		if (value == null) {
			methodSet.invoke(entity, value);
		} else {
			// 类型转换
			value = typeConvert(pd.getReadMethod().getReturnType(), value);
			methodSet.invoke(entity, value = value.equals("") ? null : value);
		}
	}

	public static Object typeConvert(Class<?> typeClass, Object value) {
		if (typeClass == int.class || typeClass == Integer.class) {
			return Integer.valueOf(value.toString()).intValue();
		} else if (typeClass == String.class) {
			return value.toString();
		} else if (typeClass == Float.class) {
			return Float.valueOf(value.toString()).floatValue();
		} else if (typeClass == long.class || typeClass == Long.class) {
			return Long.valueOf(value.toString()).longValue();
		} else if (typeClass == double.class || typeClass == Double.class) {
			return Double.valueOf(value.toString()).doubleValue();
		}else if (typeClass == java.sql.Date.class) {
			if (value.getClass() == String.class) {
				return java.sql.Date.valueOf(value.toString());
			} else {
				return value;
			}
		} else if (typeClass == java.util.Date.class) {
			if (value.getClass() == String.class) {
				if(value.toString().length() > 10) {
					return DateStrConvert.strToDate(value.toString(), "yyyy-MM-dd HH:mm:ss");
				} else {
					return DateStrConvert.strToDate(value.toString(), "yyyy-MM-dd");
				}
			} else {
				return value;
			}
		} else {
			return null;
		}

	}

}
