package com.onefool.auth.controller;


import com.onefool.common.domain.entry.UmsSysUser;
import com.onefool.common.pojo.PageInfo;
import com.onefool.common.pojo.PageRequestDto;
import com.onefool.common.pojo.Result;
import com.onefool.common.service.UmsSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author linjiawei
 * @Date 2023/12/25 19:08
 */
@RestController
@RequestMapping("/umsSysUser")
public class UmsSysUserController {

    @Autowired
    private UmsSysUserService userService;

    /**
     * 根据条件分页查询所有用户
     * @param umsSysUser
     * @return
     */
    @PostMapping("/getAll")
    public Result<PageInfo<UmsSysUser>> getAll(@RequestBody PageRequestDto<UmsSysUser> umsSysUser){
        return userService.getAll(umsSysUser);
    }

    /**
     * 更新用户
     * @param umsSysUser
     * @return
     */
    @PostMapping("/updateOne")
    public Result<String> updateOne(@RequestBody UmsSysUser umsSysUser){
        return userService.updateOne(umsSysUser);
    }


    /**
     * 新增用户
     * @param umsSysUser
     * @return
     */
    @PostMapping("/insert")
    public Result<String> insertSysUser(@RequestBody UmsSysUser umsSysUser){
        return userService.insertSysUser(umsSysUser);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/deleteOne/{id}")
    public Result<String> deleteOne(@PathVariable("id") Long id){
        return userService.deleteOne(id);
    }

}
