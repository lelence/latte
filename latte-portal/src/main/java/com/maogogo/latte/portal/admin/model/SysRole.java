package com.maogogo.latte.portal.admin.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
public class SysRole implements GrantedAuthority, Serializable {

    private String id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
