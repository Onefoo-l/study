package com.onefool.common.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.onefool.common.domain.entry.UmsMenu;
import com.onefool.common.domain.vo.RouterVO;
import com.onefool.common.pojo.Result;

import java.util.List;

/**
* @author Onefool
* @description 针对表【ums_menu】的数据库操作Service
* @createDate 2023-12-24 10:51:50
*/
public interface UmsMenuService extends IService<UmsMenu> {

    /**
     * 查询自己的菜单
     */
    Result<List<RouterVO>> searchSelfMenu();
}
