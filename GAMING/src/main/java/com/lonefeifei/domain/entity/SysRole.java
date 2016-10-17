package com.lonefeifei.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by baidu on 16/8/30.
 */
@Entity
public class SysRole implements Serializable {

    private static final long serialVersionUID = -4464822917573321533L;

    @Id
    @GeneratedValue
    private Long id;

    private String zone = "default";
    private String name;

    public String getName() {
        return name;
    }

    public SysRole setName(String name) {
        this.name = name;
        return this;
    }

    public String getZone() {
        return zone;
    }

    public SysRole setZone(String zone) {
        this.zone = zone;
        return this;
    }

}
