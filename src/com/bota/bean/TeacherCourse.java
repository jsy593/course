package com.bota.bean;

import java.util.Date;

public class TeacherCourse {
    private Long id;

    private Long courseid;

    private String content;

    private Integer isagree;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsagree() {
        return isagree;
    }

    public void setIsagree(Integer isagree) {
        this.isagree = isagree;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}