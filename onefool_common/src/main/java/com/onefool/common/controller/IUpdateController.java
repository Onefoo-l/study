package com.onefool.common.controller;


import com.onefool.common.pojo.Result;

/***
 * 描述
 * @author Onefool
 * @version 1.0
 */
public interface IUpdateController<T> {

    /**
     * 根据对象进行更新 根据ID
     *
     * @param record
     * @return
     */
    Result updateByPrimaryKey(T record);
}
