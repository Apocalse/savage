package com.kaltsit.entity.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ESTestUser {
    private String id;
    private String name;
    private long age;
    private String describe;
    private boolean delFlag;
    @JsonProperty(value = "@timestamp")
    private Date timestamp;
}
