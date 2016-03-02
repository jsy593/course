package com.bota.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bota.bean.College;
import com.bota.service.CollegeService;

@Controller
public class CollegeController {
	
	@Autowired
	private CollegeService collegeService;
	
	
	@RequestMapping("collegePage")
	public String collegePage(){
		return "college/college";
	}
	
	/**
	 * 查询所有的学院
	 * @return
	 */
	@RequestMapping("collegeList")
	@ResponseBody
	public List<Map<String, Object>> selectAllCollege(){
		return collegeService.selectAllCollege();
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
	 * 修改学院的信息
	 * @param college
	 * @return
	 */
	@RequestMapping("updateCollege")
	@ResponseBody
	public boolean updateById(College college){
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
	
	public static void main(String[] args) {
		System.out.println(1/5 +1);
	}
	
}
