package com.onefool.common.controller;



import com.onefool.common.pojo.Result;

import java.io.Serializable;
import java.util.List;

/***
 * 描述
 * @author Onefool
 * @version 1.0
 */
public interface ISelectController<T> {
    //根据ID 获取信息
    public Result<T> findById(Serializable id);

    //根据ID 获取信息列表
    public Result<List<T>> findAll();

    //根据条件查询   where xxx=? and yyy=?
    public Result<List<T>> findByRecord(T record);

}
