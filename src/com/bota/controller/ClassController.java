package com.bota.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bota.bean.Classes;
import com.bota.service.ClassService;
import com.bota.service.MajorService;
import com.bota.util.DateStrConvert;
import com.bota.util.MapAction;

@Controller
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private MajorService majorService;
	
	@RequestMapping("addClassPage")
	public ModelAndView addclassPage(){
		List<Map<String, Object>> listMap = majorService.selectAllMajor();
		Map<String,Object> map = new HashMap<String, Object>();
		ModelAndView model = new ModelAndView();
		model.setViewName("classes/addClass");
		model.addObject(map);
		model.addObject("majors", listMap);
		return model;
	}
	
	@RequestMapping("editClassPage")
	public String editclassPage(long id,HttpServletRequest request){
		Classes clazz = classService.selectOne(id);
		List<Map<String, Object>> listMap = majorService.selectAllMajor();
		request.setAttribute("majors", listMap);
		request.setAttribute("clazz", clazz);
		return "classes/editClass";
	}
	
	/**
	 * 查询所有的班级
	 * @return
	 */
	@RequestMapping("classList")
	@ResponseBody
	public List<Map<String, Object>> selectAllclass(){
		return classService.selectAllClasses();
	}
	
	/**
	 * 分页查询所有的班级
	 * @return
	 */
	
	@RequestMapping("classListByPage")
	public String selectAllclass(int pageNum,int pageSize,HttpServletRequest request){
		request.setAttribute("classes", classService.selectAllClasses(pageNum,pageSize));
		Map<String, Object> numberMap = classService.selectClassesNumber();
		if(numberMap != null && numberMap.size() > 0){
			int count = Integer.parseInt(numberMap.get("count").toString());
			int totalPage  = 0;
			if(count % 5 != 0 ){
				totalPage =count/5 + 1; 
			}else{
				totalPage =count/5;
			}
			request.setAttribute("count",numberMap.get("count"));
			request.setAttribute("totalPage",totalPage);
		}
		request.setAttribute("pageNum", pageNum);
		
		return "classes/classes";
	}
	
	/**
	 * 根据id查询班级
	 * @param id
	 * @return
	 */
	@RequestMapping("selectClass")
	@ResponseBody
	public Classes selectOne(long id){
		return classService.selectOne(id);
	}
	
	
	/**
	 * 添加班级
	 * @param id
	 * @return
	 */
	@RequestMapping("addClass")
	@ResponseBody
	public boolean addclass(MapAction mapVo){
		Map<String, Object> map = mapVo.getMapVo();
		Classes clazz = new Classes();
		clazz.setName(map.get("name").toString());
		clazz.setMajorid(Long.parseLong(map.get("majorid").toString()));
		Date date = DateStrConvert.strToDate(map.get("createtime").toString(), "yyyy-MM-dd");
		clazz.setCreatetime(date);
		return classService.addClass(clazz);
	}
	
	
	/**
	 * 修改班级的信息
	 * @param class
	 * @return
	 */
	@RequestMapping("updateClass")
	@ResponseBody
	public boolean updateById(MapAction mapVo){
		Map<String, Object> map = mapVo.getMapVo();
		Classes clazz = new Classes();
		clazz.setId(Long.parseLong(map.get("id").toString()));
		clazz.setName(map.get("name").toString());
		clazz.setMajorid(Long.parseLong(map.get("majorid").toString()));
		Date date = DateStrConvert.strToDate(map.get("createtime").toString(), "yyyy-MM-dd");
		clazz.setCreatetime(date);
		return classService.updateById(clazz);
	}
	/**
	 * 根据id删除班级
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteClass")
	@ResponseBody
	public boolean deleteById(long id){
		return classService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除班级
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyClass")
	@ResponseBody
	public boolean deleteById(String ids){
		return classService.deleteByIds(ids);
	}
	
}
