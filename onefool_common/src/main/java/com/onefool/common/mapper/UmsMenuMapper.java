package com.onefool.common.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onefool.common.domain.entry.UmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Onefool
* @description 针对表【ums_menu】的数据库操作Mapper
* @createDate 2023-12-24 10:51:50
* @Entity com.onefool.auth.entry.UmsMenu
*/
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    List<UmsMenu> selectRoleAndMenu(@Param("collects") List<Long> collects);
}




