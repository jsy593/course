package com.bota.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bota.bean.Major;
import com.bota.service.MajorService;
import com.bota.util.DateStrConvert;
import com.bota.util.MapAction;

@Controller
public class MajorController {
	
	@Autowired
	private MajorService majorService;
	
	
	@RequestMapping("addMajorPage")
	public String addMajorPage(){
		return "major/addMajor";
	}
	
	@RequestMapping("editMajorPage")
	public String editMajorPage(long id,HttpServletRequest request){
		Major major = majorService.selectOne(id);
		request.setAttribute("major", major);
		return "major/editMajor";
	}
	
	/**
	 * 查询所有的学院
	 * @return
	 */
	@RequestMapping("majorList")
	@ResponseBody
	public List<Map<String, Object>> selectAllMajor(){
		return majorService.selectAllMajor();
	}
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	
	@RequestMapping("majorListByPage")
	public String selectAllMajor(int pageNum,int pageSize,HttpServletRequest request){
		request.setAttribute("majors", majorService.selectAllMajor(pageNum,pageSize));
		Map<String, Object> numberMap = majorService.selectMajorNumber();
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
		
		return "major/major";
	}
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	@RequestMapping("selectMajor")
	@ResponseBody
	public Major selectOne(long id){
		return majorService.selectOne(id);
	}
	
	
	/**
	 * 添加学院
	 * @param id
	 * @return
	 */
	@RequestMapping("addMajor")
	@ResponseBody
	public boolean addMajor(MapAction mapVo){
		Map<String, Object> map = mapVo.getMapVo();
		Major major = new Major();
		major.setName(map.get("name").toString());
		major.setCollegeid(Long.parseLong(map.get("collegeid").toString()));
		Date date = DateStrConvert.strToDate(map.get("createtime").toString(), "yyyy-MM-dd");
		major.setCreatetime(date);
		return majorService.addMajor(major);
	}
	
	
	/**
	 * 修改学院的信息
	 * @param major
	 * @return
	 */
	@RequestMapping("updateMajor")
	@ResponseBody
	public boolean updateById(MapAction mapVo){
		Map<String, Object> map = mapVo.getMapVo();
		Major major = new Major();
		major.setId(Long.parseLong(map.get("id").toString()));
		major.setName(map.get("name").toString());
		Date date = DateStrConvert.strToDate(map.get("createtime").toString(), "yyyy-MM-dd");
		major.setCreatetime(date);
		return majorService.updateById(major);
	}
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteMajor")
	@ResponseBody
	public boolean deleteById(long id){
		return majorService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除学院
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyMajors")
	@ResponseBody
	public boolean deleteById(String ids){
		return majorService.deleteByIds(ids);
	}
	
}
