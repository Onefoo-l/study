package com.onefool.common.controller;


import com.onefool.common.pojo.Result;

/***
 * 描述
 * @author Onefool
 * @version 1.0
 */
public interface IInsertController<T> {
    /**
     * 添加记录
     * @param record
     * @return
     */
    Result insert(T record);

}