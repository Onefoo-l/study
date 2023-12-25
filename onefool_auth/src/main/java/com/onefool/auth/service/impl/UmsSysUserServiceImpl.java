package com.onefool.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onefool.auth.domain.entry.UmsSysUser;
import com.onefool.auth.mapper.UmsSysUserMapper;
import com.onefool.auth.service.UmsSysUserService;
import com.onefool.common.pojo.PageInfo;
import com.onefool.common.pojo.PageRequestDto;
import com.onefool.common.pojo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Onefool
 * @description 针对表【ums_sys_user(后台用户表)】的数据库操作Service实现
 * @createDate 2023-12-24 10:41:33
 */
@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements UmsSysUserService {

    /**
     * 插入用户信息
     *
     * @param umsSysUser 用户信息
     * @return 成功返回true, 失败返回false
     */
    @Override
    public Result<String> insertSysUser(UmsSysUser umsSysUser) {
        if (Objects.isNull(umsSysUser)) return Result.errorMessage("用户信息不能为空!!");
        int insert = this.baseMapper.insert(umsSysUser);
        return insert > 0 ? Result.ok() : Result.error();
    }

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return 成功返回true, 失败返回false
     */
    @Override
    public Result<String> deleteOne(Long id) {
        if (id == null || id <= 0) return Result.errorMessage("用户id不能为空!!!");
        int i = this.baseMapper.deleteById(id);
        return i > 0 ? Result.ok() : Result.errorMessage("删除失败!!");
    }

    /**
     * 根据分页查询所有用户信息
     *
     * @param pageUmsSysUser 用户信息
     * @return 用户信息集合
     */
    @Override
    public Result<PageInfo<UmsSysUser>> getAll(PageRequestDto<UmsSysUser> pageUmsSysUser) {
        var lmd = new LambdaQueryWrapper<UmsSysUser>();
        UmsSysUser userBody = pageUmsSysUser.getBody();
//        var pageU = new Page<UmsSysUser>(pageUmsSysUser.getPage(), pageUmsSysUser.getSize());
        var pageU = new Page<UmsSysUser>(1,1);
        if (Objects.isNull(pageUmsSysUser.getBody())) {
            lmd.orderByDesc(UmsSysUser::getUpdateTime);
            Page<UmsSysUser> umsSysUserPage = this.page(pageU,lmd);
            return Result.ok(new PageInfo<UmsSysUser>(
                    umsSysUserPage.getCurrent(),
                    umsSysUserPage.getSize(),
                    umsSysUserPage.getTotal(),
                    umsSysUserPage.getPages(),
                    umsSysUserPage.getRecords()
                    ));
        } else {
            if (!StringUtils.isEmpty(userBody.getUsername())) lmd.like(UmsSysUser::getUsername, userBody.getUsername());
            if (!StringUtils.isEmpty(userBody.getNickname())) lmd.like(UmsSysUser::getNickname, userBody.getNickname());
            if (!StringUtils.isEmpty(userBody.getEmail())) lmd.like(UmsSysUser::getEmail, userBody.getEmail());
            if (!StringUtils.isEmpty(userBody.getMobile())) lmd.like(UmsSysUser::getMobile, userBody.getMobile());
            if (userBody.getSex() != null && userBody.getSex() < 3 && userBody.getSex() >= 0)
                lmd.eq(UmsSysUser::getSex, userBody.getSex());
            if (!StringUtils.isEmpty(userBody.getAvatar())) lmd.like(UmsSysUser::getAvatar, userBody.getAvatar());
            if (!StringUtils.isEmpty(userBody.getRemark())) lmd.like(UmsSysUser::getRemark, userBody.getRemark());
            if (userBody.getStatus() != null && userBody.getStatus() == 0) lmd.eq(UmsSysUser::getStatus, 0);
            if (!StringUtils.isEmpty(userBody.getUpdater())) lmd.like(UmsSysUser::getUpdater, userBody.getUpdater());
            if (!StringUtils.isEmpty(userBody.getRemark())) lmd.like(UmsSysUser::getRemark, userBody.getRemark());
            if (userBody.getCreateTime() != null) lmd.like(UmsSysUser::getCreateTime, userBody.getCreateTime());
            if (userBody.getUpdateTime() != null) {
                lmd.like(UmsSysUser::getUpdateTime, userBody.getUpdateTime());
                lmd.orderByDesc(UmsSysUser::getUpdateTime);
                }
        }
        lmd.eq(UmsSysUser::getDeleted, 0);
        Page<UmsSysUser> page = this.page(pageU, lmd);

        return Result.ok(new PageInfo<UmsSysUser>(
                page.getCurrent(),
                page.getSize(),
                page.getTotal(),
                page.getPages(),
                page.getRecords()
        ));
    }

    /**
     * 更新用户信息
     *
     * @param umsSysUser 用户信息
     * @return 成功返回true, 失败返回false
     */
    @Override
    public Result<String> updateOne(UmsSysUser umsSysUser) {
        var lmd = new LambdaQueryWrapper<UmsSysUser>();
        lmd.eq(UmsSysUser::getId, umsSysUser.getId());
        boolean update = this.update(umsSysUser, lmd);
        return update ? Result.ok() : Result.errorMessage("更新失败!!");
    }
}




