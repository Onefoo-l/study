package com.onefool.common.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onefool.common.domain.entry.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Onefool
* @description 针对表【ums_sys_user(后台用户表)】的数据库操作Mapper
* @createDate 2023-12-24 10:41:33
* @Entity .com.onefool.auth.entry.UmsSysUser
*/
@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {

    UmsSysUser selectSysUserAndRole(@Param("accountType") int accountType, @Param("account") String account);
}




