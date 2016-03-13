package com.bota.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bota.bean.College;
import com.bota.service.CollegeService;
import com.bota.util.DateStrConvert;
import com.bota.util.MapAction;

@Controller
public class CollegeController {
	
	@Autowired
	private CollegeService collegeService;
	
	
	@RequestMapping("addCollegePage")
	public String addCollegePage(){
		return "college/addCollege";
	}
	
	@RequestMapping("editCollegePage")
	public String editCollegePage(long id,HttpServletRequest request){
		College college = collegeService.selectOne(id);
		request.setAttribute("college", college);
		return "college/editCollege";
	}
	
	/**
	 * 查询所有的学院
	 * @return
	 */
	@RequestMapping("collegeList")
	@ResponseBody
	public void selectAllCollege(HttpServletRequest request){
		request.setAttribute("colleges", collegeService.selectAllCollege());
	}
	
	/**
	 * 分页查询所有的学院
	 * @return
	 */
	
	@RequestMapping("collegeListByPage")
	public String selectAllCollege(int pageNum,int pageSize,HttpServletRequest request){
		request.setAttribute("colleges", collegeService.selectAllCollege(pageNum,pageSize));
		Map<String, Object> numberMap = collegeService.selectCollegeNumber();
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
		
		return "college/college";
	}
	
	/**
	 * 根据id查询学院
	 * @param id
	 * @return
	 */
	@RequestMapping("selectCollege")
	@ResponseBody
	public College selectOne(long id){
		return collegeService.selectOne(id);
	}
	
	
	/**
	 * 添加学院
	 * @param id
	 * @return
	 */
	@RequestMapping("addCollege")
	@ResponseBody
	public boolean addCollege(MapAction mapVo){
		Map<String, Object> map = mapVo.getMapVo();
		College college = new College();
		college.setName(map.get("name").toString());
		Date date = DateStrConvert.strToDate(map.get("createtime").toString(), "yyyy-MM-dd");
		college.setCreatetime(date);
		return collegeService.addCollege(college);
	}
	
	
	/**
	 * 修改学院的信息
	 * @param college
	 * @return
	 */
	@RequestMapping("updateCollege")
	@ResponseBody
	public boolean updateById(MapAction mapVo){
		Map<String, Object> map = mapVo.getMapVo();
		College college = new College();
		college.setId(Long.parseLong(map.get("id").toString()));
		college.setName(map.get("name").toString());
		Date date = DateStrConvert.strToDate(map.get("createtime").toString(), "yyyy-MM-dd");
		college.setCreatetime(date);
		return collegeService.updateById(college);
	}
	/**
	 * 根据id删除学院
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteCollege")
	@ResponseBody
	public boolean deleteById(long id){
		return collegeService.deleteById(id);
	}
	
	/**
	 * 根据ids批量删除学院
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteManyColleges")
	@ResponseBody
	public boolean deleteById(String ids){
		return collegeService.deleteByIds(ids);
	}
	
}
