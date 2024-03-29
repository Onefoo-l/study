package com.onefool.common.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onefool.common.domain.entry.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Onefool
* @description 针对表【ums_role】的数据库操作Mapper
* @createDate 2023-12-24 10:48:54
* @Entity com.onefool.auth.entry.UmsRole
*/
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    List<Long> selectRoleByUserId(@Param("userId") Long userId);
}




