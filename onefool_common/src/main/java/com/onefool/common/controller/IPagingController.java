package com.onefool.common.controller;


import com.onefool.common.pojo.PageInfo;
import com.onefool.common.pojo.PageRequestDto;
import com.onefool.common.pojo.Result;

/***
 * 描述
 * @author Onefool
 * @version 1.0
 */
public interface IPagingController<T> {

    /**
     * 根据查询条件 requestDto 分页查询
     * @return
     */
    Result<PageInfo<T>> findByPage(PageRequestDto<T> requestDto);


}
