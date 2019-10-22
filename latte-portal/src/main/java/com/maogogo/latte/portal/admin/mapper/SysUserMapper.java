package com.maogogo.latte.portal.admin.mapper;

import com.maogogo.latte.portal.admin.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {

    @Select("select id, username, password from tbl_sys_user where username=#{username}")
    SysUser findUserByName(@Param("username") String username);
}
