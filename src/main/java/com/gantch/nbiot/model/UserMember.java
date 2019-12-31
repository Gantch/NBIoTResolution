package com.gantch.nbiot.model;

import lombok.Data;

@Data
public class UserMember {

    private Integer id;

    private Integer tenantId;

    private String name;

    private String phone;


}