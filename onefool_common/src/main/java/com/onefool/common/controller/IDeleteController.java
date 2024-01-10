package com.onefool.common.controller;



import com.onefool.common.pojo.Result;

import java.io.Serializable;

/***
 * 描述
 * @author Onefool
 * @version 1.0
 */
public interface IDeleteController<T> {
    /**
     * 根据ID 删除
     *
     * @param id
     * @return
     */
    Result deleteById(Serializable id);
}
