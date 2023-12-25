package com.onefool.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.onefool.auth.domain.entry.UmsSysUser;
import com.onefool.common.pojo.PageInfo;
import com.onefool.common.pojo.PageRequestDto;
import com.onefool.common.pojo.Result;

/**
* @author Onefool
* @description 针对表【ums_sys_user(后台用户表)】的数据库操作Service
* @createDate 2023-12-24 10:41:33
*/
public interface UmsSysUserService extends IService<UmsSysUser> {

    /**
     *
     * @param umsSysUser
     * @return
     */
    Result<String> insertSysUser(UmsSysUser umsSysUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Result<String> deleteOne(Long id);

    /**
     * 根据分页条件查询获取所有用户
     * @param umsSysUser
     * @return
     */
    Result<PageInfo<UmsSysUser>> getAll(PageRequestDto<UmsSysUser> umsSysUser);

    /**
     * 更新用户
     * @param umsSysUser
     * @return
     */
    Result<String> updateOne(UmsSysUser umsSysUser);
}
