package com.bota.bean;

import java.util.Date;

public class Classes {
    private Long id;

    private String name;

    private Long majorid;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getMajorid() {
        return majorid;
    }

    public void setMajorid(Long majorid) {
        this.majorid = majorid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}