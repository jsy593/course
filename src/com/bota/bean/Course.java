package com.bota.bean;

import java.util.Date;

public class Course {
    private Long id;

    private Long coursenumber;

    private String name;

    private Integer credit;

    private Long teacherid;

    private String schooltime;

    private String address;

    private Long specialtyid;

    private Integer numberlimit;

    private Integer isfinish;

    private Date createtime;

    private Integer ischange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoursenumber() {
        return coursenumber;
    }

    public void setCoursenumber(Long coursenumber) {
        this.coursenumber = coursenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public String getSchooltime() {
        return schooltime;
    }

    public void setSchooltime(String schooltime) {
        this.schooltime = schooltime == null ? null : schooltime.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getSpecialtyid() {
        return specialtyid;
    }

    public void setSpecialtyid(Long specialtyid) {
        this.specialtyid = specialtyid;
    }

    public Integer getNumberlimit() {
        return numberlimit;
    }

    public void setNumberlimit(Integer numberlimit) {
        this.numberlimit = numberlimit;
    }

    public Integer getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(Integer isfinish) {
        this.isfinish = isfinish;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIschange() {
        return ischange;
    }

    public void setIschange(Integer ischange) {
        this.ischange = ischange;
    }

	@Override
	public String toString() {
		return "Course [id=" + id + ", coursenumber=" + coursenumber + ", name=" + name + ", credit=" + credit
				+ ", teacherid=" + teacherid + ", schooltime=" + schooltime + ", address=" + address + ", specialtyid="
				+ specialtyid + ", numberlimit=" + numberlimit + ", isfinish=" + isfinish + ", createtime=" + createtime
				+ ", ischange=" + ischange + "]";
	}
    
}